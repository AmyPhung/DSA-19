public class MyTest {
  private static final Cow DELILAH = new Cow("Delilah", 10, "blue");
  public static void main(String[] args) {
//    DELILAH.name = "not delilah anymore";
//    System.out.println(DELILAH.name);
//
//    int testArray[] = {3,6,3,4,8};
//    System.out.println(testArray.length);
//
//    if (testArray.length == 5) {
//      System.out.println("length is 5!");
//    }
//
//    for (int i=0; i<testArray.length; i++) {
//      System.out.println(i);
//      System.out.println(testArray[i]);
//    }
//
//
//    int arr1[] = { 0, 1, 2, 3, 4, 5 };
//
//    int arr2[] = { 5, 10, 20, 30, 40, 50 };
//
//    arr1 = arr2;

    // copies an array from the specified source array
    // System.arraycopy(arr1, 0, arr2, 0, 1);
    // System.out.print("array2 = ");
    // System.out.print(arr2[0] + " ");
    // System.out.print(arr2[1] + " ");
    // System.out.print(arr2[2] + " ");
    // System.out.print(arr2[3] + " ");
    // System.out.print(arr2[4] + " ");


    int elems[] = {0,1,2,3,4,5,6,7,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
    System.out.println(elems[8]);


    int size = 8;

    int index = 3;
    int c = 100; //new int to be added



    if (index >= size) {
      //index out of bounds exception
      throw new IndexOutOfBoundsException(index);
    }

    for (int i=size; i>index+1; i--)  {// fix syntax
      elems[i] = elems[i-1];
    }

    elems[index] = c;
    size++;

    // if array is full, double size.
    if (size == elems.length) {
      int[] newArr = new int[elems.length*2];
      System.arraycopy(elems, 0, newArr, 0, size);
    }

//    System.out.println(elems[8]);
//    System.out.println(newArr.length);
//
//    System.out.println(newArr.length);

  }
}
