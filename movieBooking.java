import java.util.*;
class movieBooking {
    private List<Movie> movies = new ArrayList<>();
    public movieBooking(){
        movies.add(new Movie("Oppenheimer","Thriller",15,15));
        movies.add(new Movie("Barbie","Comedy",15,15));
        movies.add(new Movie("Mission Impossible 7","Action",15,15));
        movies.add(new Movie("Wonka","Musical",15,15));
        movies.add(new Movie("Tiger 3","Thriller",15,15));
    }
    void browseMovies(){
        for(Movie movie:movies){
            System.out.println(movie.getName());
        }
    }
    void bookTickets(){
        System.out.println("Enter movie name: ");
        Scanner sc=new Scanner(System.in);
        String movieName=sc.nextLine();
        Optional<Movie> optionalMovie=movies.stream().filter(movie->movie.getName().equalsIgnoreCase(movieName)).findFirst();
        if(!optionalMovie.isPresent()){
            System.out.println("Movie is not present");
            return;
        }
        Movie movie=optionalMovie.get();
        System.out.println("Number of seats: "+movie.getTotalSeats());
        System.out.println("Available seats: "+movie.getAvailableSeats());
        System.out.print("Enter number of seats to book: ");
        int numSeats=sc.nextInt();
        if(numSeats>movie.getAvailableSeats()){
            System.out.println("Not enough seats available");
            return;
        }
        movie.bookSeats(numSeats);
    }
}
class Movie{
    private String name;
    private String genre;
    private boolean isBooked;
    private int totalSeats,availableSeats;
    public Movie(String name, String genre, int totalSeats, int availableSeats){
        this.name=name;
        this.genre=genre;
        this.isBooked=false;
        this.totalSeats=totalSeats;
        this.availableSeats=availableSeats;
    }
    public String getName(){
        return name;
    }
    public int getTotalSeats(){
        return totalSeats;
    }
    public int getAvailableSeats(){
        return availableSeats;
    }
    void bookSeats(int numSeats){
        if(numSeats<=availableSeats){
            availableSeats-=numSeats;
            System.out.println("Tickets booked successfully");
        }
        else
            System.out.println("Not enough seats available");
    }
}
class Main1 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        movieBooking system=new movieBooking();
        while(true){
            System.out.println("1. Browse Movies");
            System.out.println("2. Book tickets");
            System.out.println("3. Exit");
            System.out.println("Choose an option: ");
            int ch=sc.nextInt();
            sc.nextLine();
            switch(ch){
                case 1:
                    system.browseMovies();
                    break;
                case 2:
                    system.bookTickets();
                    break;
                case 3:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }
}
