package ku.cs.backendapi.service;

import ku.cs.backendapi.common.Status;
import ku.cs.backendapi.entity.Restaurant;
import ku.cs.backendapi.entity.RestaurantTableType;
import ku.cs.backendapi.entity.TableType;
import ku.cs.backendapi.exception.TableException;
import ku.cs.backendapi.exception.TokenException;
import ku.cs.backendapi.exception.UserNotFoundException;
import ku.cs.backendapi.model.TableTypeRestaurantDTO;
import ku.cs.backendapi.repository.BookingRepository;
import ku.cs.backendapi.repository.RestaurantRepository;
import ku.cs.backendapi.repository.RestaurantTableTypeRepository;
import ku.cs.backendapi.repository.TableTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

@Service
public class TableService {
    @Autowired
    private TableTypeRepository tableTypeRepository;
    @Autowired
    private RestaurantTableTypeRepository restaurantTableTypeRepository;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private RestaurantRepository restaurantRepository;
    @Autowired
    private BookingRepository bookingRepository;

    public List<TableTypeRestaurantDTO> getTableRestaurant(String tokenId) throws UserNotFoundException, TokenException {
        Restaurant restaurant = (Restaurant) tokenService.getUser(UUID.fromString(tokenId));

        List<TableType> tableTypes = tableTypeRepository.findAll();
        List<RestaurantTableType> restaurantTableTypes = restaurantTableTypeRepository.findByRestaurant(restaurant);

        ArrayList<TableTypeRestaurantDTO> tableTypeRestaurantDTOS = new ArrayList<>();

        if (restaurantTableTypes.isEmpty()) {
            for (TableType t : tableTypes) {
                RestaurantTableType restaurantTableType = new RestaurantTableType();
                restaurantTableType.setRestaurant(restaurant);
                restaurantTableType.setTableType(t);
                restaurantTableType.setNumOfTable(0);
                restaurantTableType.setId(UUID.randomUUID());
                restaurantTableTypeRepository.save(restaurantTableType);
            }
        }
        for (RestaurantTableType r : restaurantTableTypeRepository.findByRestaurant(restaurant)) {
            TableTypeRestaurantDTO tableTypeRestaurantDTO = new TableTypeRestaurantDTO();
            tableTypeRestaurantDTO.setNumTable(String.valueOf(r.getNumOfTable()));
            tableTypeRestaurantDTO.setNumSeat(String.valueOf(r.getTableType().getSeatNumber()));
            tableTypeRestaurantDTOS.add(tableTypeRestaurantDTO);
        }

        tableTypeRestaurantDTOS.sort(Comparator.comparing(TableTypeRestaurantDTO::getNumSeat));
        return tableTypeRestaurantDTOS;
    }

    public void setTable(String tokenId, String numSeat, String numTable) throws UserNotFoundException, TokenException, TableException {
        if(Integer.parseInt(numTable) < 0) throw new TableException("Table number can not less than 0");
        Restaurant restaurant = (Restaurant) tokenService.getUser(UUID.fromString(tokenId));

        RestaurantTableType restaurantTableType = restaurantTableTypeRepository.findByRestaurantAndTableType_SeatNumber(restaurant, Integer.parseInt(numSeat));
        if(restaurantTableType == null) throw new TableException("Table not found");

        restaurantTableType.setNumOfTable(Integer.parseInt(numTable));
        restaurantTableTypeRepository.save(restaurantTableType);
    }

    public List<String> getAllTableTypeRestaurant(String restaurantName) throws UserNotFoundException, TokenException {
        Restaurant restaurant = restaurantRepository.findByRestaurantName(restaurantName);
        if(restaurant == null) throw new UserNotFoundException("Restaurant not found");

        List<RestaurantTableType> restaurantTableTypes = restaurantTableTypeRepository.findByRestaurant(restaurant);

        List<String> viewRestaurantTable = new ArrayList<>();
        for(RestaurantTableType r : restaurantTableTypes) {
            if(bookingRepository.findByRestaurantAndRestaurantTableTypeAndStatus(restaurant, r, Status.IN_PROGRESS).size() < r.getNumOfTable()) {
                viewRestaurantTable.add(String.valueOf(r.getTableType().getSeatNumber()));
            }
        }
        viewRestaurantTable.sort(Comparator.comparing(s -> s));
        return viewRestaurantTable;
    }
}