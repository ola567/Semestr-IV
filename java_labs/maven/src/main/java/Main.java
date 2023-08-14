import com.google.gson.Gson;

public class Main
{
    public static void Main(String[] argv)
    {
        Book book=new Book("Tolkien", "The Lord Of Rings");
        Gson gson=new Gson();
        String bookJson= gson.toJson(book);

        System.out.println(bookJson);
    }
}
