package ru.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	public static class Order {
		int id;
		String state;

		public Order(int id, String state) {
			this.id = id;
			this.state = state;
		}

		@Override
		public String toString() {
			return "Order [id=" + id + ", state=" + state + "]";
		}

	}

	public static class Ticket {
		int id;
		String state;

		public Ticket(int id, String state) {
			this.id = id;
			this.state = state;
		}

		@Override
		public String toString() {
			return "Ticket [id=" + id + ", state=" + state + "]";
		}

	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(Application.class, args);
	}

}