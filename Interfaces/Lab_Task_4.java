// Implement the Enumeration interface in the NameCollection class
// Define the Enumeration interface
interface Enumeration {
    // Check if a value exists in the next index
    boolean hasNext(int index);

    // Return the next element in the collection as an Object
    Object getNext(int index);
}

class NameCollection implements Enumeration {
    // Array to store names
    String[] names;
    int size; // Track the number of valid elements in the array

    // Constructor to initialize the names array with a given list of names
    public NameCollection(String[] inputNames) {
        names = new String[100]; // Fixed size array
        size = inputNames.length;

        // Copy input names into the array
        for (int i = 0; i < size; i++) {
            names[i] = inputNames[i];
        }
    }

    // Check if there is a next element in the collection
    @Override
    public boolean hasNext(int index) {
        return index >= 0 && index < size;
    }

    // Return the next element in the collection
    @Override
    public Object getNext(int index) {
        if (hasNext(index)) {
            return names[index];
        } else {
            throw new IndexOutOfBoundsException("No more elements in the collection");
        }
    }
}

public class Lab_Task_4 {
    public static void main(String[] args) {
        // Create a NameCollection object with some sample names
        String[] sampleNames = { "Alice", "Bob", "Charlie", "Diana" };
        NameCollection nameCollection = new NameCollection(sampleNames);

        // Iterate through the collection using the enumeration methods
        int index = 0;
        while (nameCollection.hasNext(index)) {
            System.out.println(nameCollection.getNext(index));
            index++;
        }
    }
}
