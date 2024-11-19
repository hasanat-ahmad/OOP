interface Compare {
    boolean compareObject(Object o);
}

class InverntoryItem implements Compare {
    private String name;
    private int uniqueItemID;

    public InverntoryItem() {
    }

    public InverntoryItem(String name, int uniqueItemID) {
        this.name = name;
        this.uniqueItemID = uniqueItemID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setUniqueItemID(int uniqueItemID) {
        this.uniqueItemID = uniqueItemID;
    }

    public int getUniqueItemID() {
        return uniqueItemID;
    }

    @Override
    public boolean compareObject(Object o) {
        if (o instanceof InverntoryItem){
            InverntoryItem inverntoryItem = (InverntoryItem)(o);
            return this.uniqueItemID == inverntoryItem.uniqueItemID && this.name == inverntoryItem.name;
        }
        return false;
    }
}

public class Lab_Task_3 {
    public static void main(String[] args) {
        InverntoryItem inverntoryItem = new InverntoryItem("BAC", 45);
        InverntoryItem inverntoryItem2 = new InverntoryItem("BAC", 45);
        System.out.println(inverntoryItem.compareObject("Object1is equal to Object2" + inverntoryItem2));
    }
}