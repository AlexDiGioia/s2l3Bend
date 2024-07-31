import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<Product> products = List.of(
                new Product(1, "Lord of the Rings", "Books", 120),
                new Product(2, "Giocattolo bello", "Baby", 50),
                new Product(3, "Ciuccio", "Baby", 5),
                new Product(4, "Maglietta", "Boys", 30),
                new Product(5, "Book B", "Books", 80),
                new Product(6, "Book C", "Books", 150)
        );

        List<Customer> customers = List.of(
                new Customer(1L, "Alice", 1),
                new Customer(2L, "Bob", 2)
        );

        List<Order> orders = List.of(
                new Order(1L, "Delivered", LocalDate.of(2021, 2, 15), LocalDate.of(2021, 2, 20),
                        List.of(products.get(0), products.get(1)), customers.get(1)),
                new Order(2L, "Shipped", LocalDate.of(2021, 3, 5), LocalDate.of(2021, 3, 10),
                        List.of(products.get(2), products.get(4)), customers.get(1))
        );


        // ---------ES1. Ottenere una lista di prodotti nella categoria "Books" con prezzo > 100
        List<Product> expensiveBooks = products.stream()
                .filter(p -> "Books".equals(p.getCategory()) && p.getPrice() > 100)
                .toList();
        System.out.println("Prodotti nella categoria 'Books' con prezzo > 100: " + expensiveBooks);


        // ---------ES2. Ottenere una lista di ordini con prodotti nella categoria "Baby"
        List<Order> ordersWithBabyProducts = orders.stream()
                .filter(order -> order.getProducts().stream()
                        .anyMatch(product -> "Baby".equals(product.getCategory())))
                .toList();
        System.out.println("Ordini con prodotti nella categoria 'Baby': " + ordersWithBabyProducts);


        // -----------ES3. Ottenere una lista di prodotti nella categoria "Boys" con 10% di sconto
        List<Product> discountedBoysProducts = products.stream()
                .filter(p -> "Boys".equals(p.getCategory()))
                .peek(p -> p.setPrice(p.getPrice() * 0.9)) // applicare 10% di sconto
                .toList();
        System.out.println("Prodotti nella categoria 'Boys' con sconto del 10%: " + discountedBoysProducts);

        // -----------ES4. Ottenere una lista di prodotti ordinati da clienti di livello 2 tra il 01-Feb-2021 e il 01-Apr-2021
        LocalDate startDate = LocalDate.of(2021, 2, 1);
        LocalDate endDate = LocalDate.of(2021, 4, 1);
        List<Product> productsOrderedByTier2 = orders.stream()
                .filter(order -> order.getCustomer().getTier() == 2)
                .filter(order -> !order.getOrderDate().isBefore(startDate) && !order.getOrderDate().isAfter(endDate))
                .flatMap(order -> order.getProducts().stream())
                .toList();
        System.out.println("Prodotti ordinati da clienti di livello 2 tra il 01-Feb-2021 e il 01-Apr-2021: " + productsOrderedByTier2);
    }
}
