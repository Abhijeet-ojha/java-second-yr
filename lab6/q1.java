import java.io.*;

class HotelRoomManager {
    static final int NAME_LENGTH = 20; // fixed length
    static final int RECORD_SIZE = 4 + (2 * NAME_LENGTH) + 8 + 1;

    // Utility: write fixed-length string
    public static void writeString(RandomAccessFile raf, String str) throws IOException {
        StringBuilder sb = new StringBuilder(str);
        sb.setLength(NAME_LENGTH); // pad or trim
        raf.writeChars(sb.toString());
    }

    // Utility: read fixed-length string
    public static String readString(RandomAccessFile raf) throws IOException {
        char[] chars = new char[NAME_LENGTH];
        for (int i = 0; i < NAME_LENGTH; i++) {
            chars[i] = raf.readChar();
        }
        return new String(chars).trim();
    }

    // Add new room
    public static void addRoom(int roomNo, String type, double price, boolean status) throws IOException {
        RandomAccessFile raf = new RandomAccessFile("hotel.dat", "rw");

        long pos = (roomNo - 1) * RECORD_SIZE;
        raf.seek(pos);

        raf.writeInt(roomNo);
        writeString(raf, type);
        raf.writeDouble(price);
        raf.writeBoolean(status);

        raf.close();
        System.out.println("Room added successfully!");
    }

    // View room details
    public static void viewRoom(int roomNo) throws IOException {
        RandomAccessFile raf = new RandomAccessFile("hotel.dat", "r");

        long pos = (roomNo - 1) * RECORD_SIZE;
        if (pos >= raf.length()) {
            System.out.println("Room not found!");
            raf.close();
            return;
        }

        raf.seek(pos);

        int rNo = raf.readInt();
        String type = readString(raf);
        double price = raf.readDouble();
        boolean status = raf.readBoolean();

        System.out.println("Room No: " + rNo);
        System.out.println("Type: " + type);
        System.out.println("Price: " + price);
        System.out.println("Booked: " + status);

        raf.close();
    }

    // Update booking status
    public static void updateStatus(int roomNo, boolean newStatus) throws IOException {
        RandomAccessFile raf = new RandomAccessFile("hotel.dat", "rw");

        long pos = (roomNo - 1) * RECORD_SIZE;
        if (pos >= raf.length()) {
            System.out.println("Room not found!");
            raf.close();
            return;
        }

        raf.seek(pos);

        raf.readInt();                // skip roomNo
        raf.skipBytes(2 * NAME_LENGTH); // skip string
        raf.readDouble();             // skip price

        raf.writeBoolean(newStatus);  // update status

        raf.close();
        System.out.println("Status updated!");
    }

    // Main menu
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            System.out.println("\n1. Add Room\n2. View Room\n3. Update Status\n4. Exit");
            int choice = Integer.parseInt(br.readLine());

            switch (choice) {
                case 1:
                    System.out.print("Room No: ");
                    int r = Integer.parseInt(br.readLine());
                    System.out.print("Type: ");
                    String t = br.readLine();
                    System.out.print("Price: ");
                    double p = Double.parseDouble(br.readLine());
                    System.out.print("Booked (true/false): ");
                    boolean s = Boolean.parseBoolean(br.readLine());
                    addRoom(r, t, p, s);
                    break;

                case 2:
                    System.out.print("Room No: ");
                    viewRoom(Integer.parseInt(br.readLine()));
                    break;

                case 3:
                    System.out.print("Room No: ");
                    int rn = Integer.parseInt(br.readLine());
                    System.out.print("New Status (true/false): ");
                    boolean ns = Boolean.parseBoolean(br.readLine());
                    updateStatus(rn, ns);
                    break;

                case 4:
                    System.exit(0);
            }
        }
    }
}