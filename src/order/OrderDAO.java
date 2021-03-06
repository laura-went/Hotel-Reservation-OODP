package order;

//java apis
import java.util.ArrayList;
//original packages
import data.DataUtil;
import data.Hotel;
import guest.Guest;

public class OrderDAO {
	
	// load data access
	private DataUtil dataUtil = new DataUtil();

	protected void resetOrders() {
		Hotel hotel = dataUtil.readHotel();
		ArrayList<Order> orders = new ArrayList<>();
		hotel.setOrders(orders);
		dataUtil.write(hotel);
	}

	// implementations
	private Order getItemById(ArrayList<Order> orders, String id) {
		Order orderMatchingId = null;
		
		for (Order order : orders) {
			if (order.getId().equals(id)) {
				orderMatchingId = order;
				break;
			}
		}
		
		return orderMatchingId;
	}
	
	//interfaces
	public ArrayList<Order> getAllItem() {
		Hotel hotel = dataUtil.readHotel();
		ArrayList<Order> orders = hotel.getOrders();
		
		return orders;
	}
	
	public Order getItemById(String id) {
		ArrayList<Order> orders = getAllItem();
		Order orderMatchingId = null;
		
		for (Order order : orders) {
			if (order.getId().equals(id)) {
				orderMatchingId = order;
				break;
			}
		}
		
		return orderMatchingId;
	}
	
	public void add(Order orderInput) {
		Hotel hotel = dataUtil.readHotel();
		ArrayList<Order> orders = hotel.getOrders();
		
		orders.add(orderInput);
		dataUtil.write(hotel);
	}
	
	public void update(Order orderInput) {
		Hotel hotel = dataUtil.readHotel();
		ArrayList<Order> orders = hotel.getOrders();
		Order orderMatchingId = getItemById(orders, orderInput.getId());
		
		if (orderMatchingId == null) {
			System.out.println("Cannot find order matching id.");
			return;
		}
		
		orderMatchingId.setRemarks(orderInput.getRemarks());
		orderMatchingId.setStatus(orderInput.getStatus());
		dataUtil.write(hotel);
	}

}
