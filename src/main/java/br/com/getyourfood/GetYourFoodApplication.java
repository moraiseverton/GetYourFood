package br.com.getyourfood;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import br.com.getyourfood.cousine.Cousine;
import br.com.getyourfood.cousine.CousineRepository;
import br.com.getyourfood.product.Product;
import br.com.getyourfood.product.ProductRepository;
import br.com.getyourfood.store.Store;
import br.com.getyourfood.store.StoreRepository;

@SpringBootApplication
public class GetYourFoodApplication {

    public static void main(String[] args) {
        SpringApplication.run(GetYourFoodApplication.class, args);
    }

    @Bean
    ApplicationRunner init(CousineRepository cousineRepository, StoreRepository storeRepository, ProductRepository productRepository) {
        return args -> {
            Cousine chinese = new Cousine("Chinese");
            cousineRepository.save(chinese);

            Cousine pizza = new Cousine("Pizza");
            cousineRepository.save(pizza);

            Cousine vietnamese = new Cousine("Vietnamese");
            cousineRepository.save(vietnamese);

            Cousine sushi = new Cousine("Sushi");
            cousineRepository.save(sushi);

            Store haiShang = new Store();
            haiShang.setName("Hai Shang");
            haiShang.setAddress("2991 Pembina Hwy, Winnipeg, Manitoba R3T 2H5, Canada");
            haiShang.setCousine(chinese);
            storeRepository.save(haiShang);

            Store yes = new Store();
            yes.setName("Ye's");
            yes.setAddress("616 St James St, Winnipeg, Manitoba R3G 3J5, Canada");
            yes.setCousine(chinese);
            storeRepository.save(yes);

            Store goodEarth = new Store();
            goodEarth.setName("Good Earth Chop Suey House");
            goodEarth.setAddress("1849 Portage Ave, Winnipeg, Manitoba R3J 0G8, Canada");
            goodEarth.setCousine(chinese);
            storeRepository.save(goodEarth);

            Store zaPizza = new Store();
            zaPizza.setName("Za Pizza Bistro");
            zaPizza.setAddress("E-1220 St Mary's Rd, Winnipeg, Manitoba R2M 3V6, Canada");
            zaPizza.setCousine(pizza);
            storeRepository.save(zaPizza);

            Store santaLucia = new Store();
            santaLucia.setName("Santa Lucia Pizza Winnipeg");
            santaLucia.setAddress("4 St Mary's Rd, Winnipeg, Manitoba R2H 1H1, Canada");
            santaLucia.setCousine(pizza);
            storeRepository.save(santaLucia);

            Store carbone = new Store();
            carbone.setName("Carbone Coal Fired Pizza");
            carbone.setAddress("400-1580 Taylor Avenue, Winnipeg Manitoba R3N 2A7, Canada");
            carbone.setCousine(pizza);
            storeRepository.save(carbone);

            Store phoKim = new Store();
            phoKim.setName("Pho Kim Tuong");
            phoKim.setAddress("856 Ellice Ave, Winnipeg, Manitoba R3G 0C4, Canada");
            phoKim.setCousine(vietnamese);
            storeRepository.save(phoKim);

            Store phoHoang = new Store();
            phoHoang.setName("Pho Hoang");
            phoHoang.setAddress("794 Sargent Ave, Winnipeg, Manitoba R3E 0B7, Canada");
            phoHoang.setCousine(vietnamese);
            storeRepository.save(phoHoang);

            Store vivaRestaurant = new Store();
            vivaRestaurant.setName("Viva Restaurant");
            vivaRestaurant.setAddress("1-505 Sargent Ave, Winnipeg, Manitoba R3B 1V9, Canada");
            vivaRestaurant.setCousine(vietnamese);
            storeRepository.save(vivaRestaurant);

            Store blufish = new Store();
            blufish.setName("Blufish");
            blufish.setAddress("179 Bannatyne Ave, Winnipeg, Manitoba R3B 0R4, Canada");
            blufish.setCousine(sushi);
            storeRepository.save(blufish);

            Store wasabiSabi = new Store();
            wasabiSabi.setName("Wasabi Sabi");
            wasabiSabi.setAddress("Taylor Avenue, Winnipeg, Manitoba, Canada");
            wasabiSabi.setCousine(sushi);
            storeRepository.save(wasabiSabi);

            Store sushiN = new Store();
            sushiN.setName("Sushi N");
            sushiN.setAddress("726 St Anne's Rd, Southglen Shopping Mall");
            sushiN.setCousine(sushi);
            storeRepository.save(sushiN);

            Product product;

            product = new Product();
            product.setStore(haiShang);
            product.setName("Shrimp Tempura");
            product.setDescription("Fresh shrimp battered and deep fried until golden brown");
            product.setPrice(10.95);
            productRepository.save(product);

            product = new Product();
            product.setStore(haiShang);
            product.setName("Shrimp with Snow Peas and Cashew");
            product.setDescription("A delicious combination of fresh shrimp, snow peas, and cashew");
            product.setPrice(12.5);
            productRepository.save(product);

            product = new Product();
            product.setStore(haiShang);
            product.setName("Special Deep-Fried Fish");
            product.setDescription("Tilapia fish deep fried until flaky and tender");
            product.setPrice(12.95);
            productRepository.save(product);

            product = new Product();
            product.setStore(yes);
            product.setName("BBQ Pork Egg Foo Yung");
            product.setDescription("Chinese omelette filled with barbequed pork");
            product.setPrice(10.95);
            productRepository.save(product);

            product = new Product();
            product.setStore(yes);
            product.setName("Beef Egg Foo Yung");
            product.setDescription("A Chinese omelette mixed with beef");
            product.setPrice(10.95);
            productRepository.save(product);

            product = new Product();
            product.setStore(yes);
            product.setName("Mushroom Egg Foo Yung");
            product.setDescription("A Chinese omelette mixed with sliced mushrooms");
            product.setPrice(9.95);
            productRepository.save(product);

            product = new Product();
            product.setStore(goodEarth);
            product.setName("BBQ Pork, Chicken, or Mushroom Chop Suey");
            product.setPrice(8.75);
            productRepository.save(product);

            product = new Product();
            product.setStore(goodEarth);
            product.setName("Deluxe Chop Suey");
            product.setDescription("Shrimp, barbeque pork, and chicken");
            product.setPrice(9.95);
            productRepository.save(product);

            product = new Product();
            product.setStore(goodEarth);
            product.setName("Plain Fried Rice");
            product.setPrice(7.5);
            productRepository.save(product);

            product = new Product();
            product.setStore(zaPizza);
            product.setName("10\" Small Greek Pizza");
            product.setDescription("Green or black olives, onions, tomato, and feta cheese");
            product.setPrice(8.99);
            productRepository.save(product);

            product = new Product();
            product.setStore(zaPizza);
            product.setName("12\" Medium Deluxe Pizza");
            product.setDescription("Pepperoni, bacon, mushrooms, onions, and green peppers");
            product.setPrice(12.99);
            productRepository.save(product);

            product = new Product();
            product.setStore(zaPizza);
            product.setName("Pizza and Wings Special");
            product.setDescription("One twelve-inch medium pizza with two toppings and twelve chicken wings");
            product.setPrice(16.99);
            productRepository.save(product);

            product = new Product();
            product.setStore(santaLucia);
            product.setName("10' Garden Fresh Pizza");
            product.setDescription("Green peppers, onions, mushrooms, black olives, and Roma tomatoes");
            product.setPrice(14.0);
            productRepository.save(product);

            product = new Product();
            product.setStore(santaLucia);
            product.setName("12' Tuscan Six-Cheese Pizza");
            product.setDescription("An authentic blend of Parmesan, Romano, Asiago, fontina, provolone, and 100% real cheese made from mozzarella");
            product.setPrice(19.0);
            productRepository.save(product);

            product = new Product();
            product.setStore(santaLucia);
            product.setName("14' Hawaiian Chicken Pizza");
            product.setDescription("A sweet and savory combination of juicy pineapple and grilled chicken");
            product.setPrice(22.0);
            productRepository.save(product);

            product = new Product();
            product.setStore(carbone);
            product.setName("Medium One-Topping Pie");
            product.setDescription("A 12-inch, medium pizza made of famous, fresh, house-made dough, cooked with one topping of your choice");
            product.setPrice(15.0);
            productRepository.save(product);

            product = new Product();
            product.setStore(carbone);
            product.setName("X-Large One-Topping Pie");
            product.setDescription("An extra large, 16-inch pizza made of famous, fresh, house-made dough, cooked with one topping of your choice");
            product.setPrice(23.0);
            productRepository.save(product);

            product = new Product();
            product.setStore(carbone);
            product.setName("Medium BBQ Pulled Pork Pie");
            product.setDescription("A 12-inch pizza made of famous, fresh, and house-made dough, topped with Caribbean jerk chicken, pineapple, onions, feta, and mozzarella cheese");
            product.setPrice(19.0);
            productRepository.save(product);

            product = new Product();
            product.setStore(phoKim);
            product.setName("Pork Spring Roll (6 pcs) - Cha Gio (6 Cuon)");
            product.setDescription("Served with fish sauce. One order comes with six spring rolls");
            product.setPrice(7.5);
            productRepository.save(product);

            product = new Product();
            product.setStore(phoKim);
            product.setName("Butterfly Shrimp - Tom Chien");
            product.setDescription("Served with sweet chilli sauce");
            product.setPrice(10.5);
            productRepository.save(product);

            product = new Product();
            product.setStore(phoKim);
            product.setName("Deluxe Wonton Soup (15 pcs) - Hoanh Thanh Sup Thap Cam (15)");
            product.setDescription("One order comes with fifteen wontons");
            product.setPrice(10.35);
            productRepository.save(product);

            product = new Product();
            product.setStore(phoHoang);
            product.setName("Grilled Pork Skewers (2 pcs)");
            product.setDescription("House-marinated ground pork skewers with roasted peanuts and bacon. One order comes with two skewers");
            product.setPrice(5.75);
            productRepository.save(product);

            product = new Product();
            product.setStore(phoHoang);
            product.setName("Chicken Wang-Yo!");
            product.setDescription("Stuffed minced chicken, carrot, green onions, mushrooms, black pepper, and bean thread noodles");
            product.setPrice(9.95);
            productRepository.save(product);

            product = new Product();
            product.setStore(phoHoang);
            product.setName("Beef Stew");
            product.setDescription("Beef flank, carrot, jumbo onion, scallions, cilantro, mint, and Thai basil. Served with a side of baguette and broth");
            product.setPrice(8.75);
            productRepository.save(product);

            product = new Product();
            product.setStore(vivaRestaurant);
            product.setName("Tripe and Flank Pho - Nam Long");
            product.setDescription("A tasty blend of beef tripe and flank served in a steaming-hot rice noodle soup");
            product.setPrice(8.99);
            productRepository.save(product);

            product = new Product();
            product.setStore(vivaRestaurant);
            product.setName("Charbroiled Pork - Thit Nuong");
            product.setDescription("Tender charbroiled pork served atop a bed of vermicelli noodles with iceberg lettuce, bean sprouts, cucumbers, pickled carrot, and radish");
            product.setPrice(9.99);
            productRepository.save(product);

            product = new Product();
            product.setStore(vivaRestaurant);
            product.setName("Charbroiled Beef - Bo Nuong");
            product.setDescription("Savoury charbroiled beef served atop a bed of vermicelli noodles with iceberg lettuce, bean sprouts, cucumbers, pickled carrot, and radish");
            product.setPrice(10.49);
            productRepository.save(product);

            product = new Product();
            product.setStore(blufish);
            product.setName("Assorted Sashimi");
            product.setDescription("Fresh assorted sashimi, hand-picked and thinly sliced by the chef");
            product.setPrice(20.0);
            productRepository.save(product);

            product = new Product();
            product.setStore(blufish);
            product.setName("Sake (Salmon) Sashimi");
            product.setDescription("Thinly sliced raw salmon served on its own");
            product.setPrice(9.0);
            productRepository.save(product);

            product = new Product();
            product.setStore(blufish);
            product.setName("Red Tuna Sashimi");
            product.setDescription("Thinly sliced red tuna, served without rice");
            product.setPrice(13.0);
            productRepository.save(product);

            product = new Product();
            product.setStore(wasabiSabi);
            product.setName("Ebi - Nigiri (1 pc)");
            product.setDescription("Steamed shrimp, served on a small bed of pressed sushi rice. One order comes with one piece");
            product.setPrice(2.5);
            productRepository.save(product);

            product = new Product();
            product.setStore(wasabiSabi);
            product.setName("Hamachi - Nigiri (1 pc)");
            product.setDescription("Thinly sliced yellowtail served on a small bed of pressed sushi rice. One order comes with one piece");
            product.setPrice(3.5);
            productRepository.save(product);

            product = new Product();
            product.setStore(wasabiSabi);
            product.setName("Ika - Nigiri (1 pc)");
            product.setDescription("Thin-sliced squid served on a small bed of pressed sushi rice. One order comes with one piece");
            product.setPrice(2.5);
            productRepository.save(product);

            product = new Product();
            product.setStore(sushiN);
            product.setName("Salmon Sushi (2 pcs)");
            product.setDescription("Fresh, raw salmon wrapped with rice and seaweed. One order comes with two pieces");
            product.setPrice(5.45);
            productRepository.save(product);

            product = new Product();
            product.setStore(sushiN);
            product.setName("Masago Sushi (2 pcs)");
            product.setDescription("Red caviar wrapped with rice and seaweed. One order comes with two pieces");
            product.setPrice(4.75);
            productRepository.save(product);

            product = new Product();
            product.setStore(sushiN);
            product.setName("Amaebi Sushi (2 pcs)");
            product.setDescription("Sweet shrimp wrapped in rice and seaweed. One order comes with two pieces");
            product.setPrice(5.75);
            productRepository.save(product);

        };
    }
}
