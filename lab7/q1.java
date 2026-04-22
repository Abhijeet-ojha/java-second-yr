package lab7;

class Room<T, U> {
    private T roomId;
    private U roomDetail;

    public Room(T roomId, U roomDetail) {
        this.roomId = roomId;
        this.roomDetail = roomDetail;
    }

    public void display() {
        System.out.println("Room ID: " + roomId +
                           ", Detail: " + roomDetail);
    }
}
