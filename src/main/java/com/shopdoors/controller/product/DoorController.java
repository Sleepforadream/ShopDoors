package com.shopdoors.controller.product;

import com.shopdoors.dao.entity.product.door.EntryDoor;
import com.shopdoors.dao.entity.product.door.RoomDoor;
import com.shopdoors.service.ImageService;
import com.shopdoors.service.product.EntryDoorService;
import com.shopdoors.service.product.RoomDoorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class DoorController {

    private final EntryDoorService entryDoorService;
    private final RoomDoorService roomDoorService;
    private final ImageService imageService;

    @GetMapping("/entry-doors")
    public String getEntryDoors(
        @RequestParam(required = false, defaultValue = "name") String sortBy,
        @RequestParam(required = false, defaultValue = "asc") String order,
        @RequestParam(required = false) String fabric,
        @RequestParam(required = false) String metal,
        @RequestParam(required = false) String color,
        @RequestParam(required = false) String filling,
        Model model) {

            List<EntryDoor> entryDoors = entryDoorService.getFilteredEntryDoors(
                    sortBy, order, fabric, metal, color, filling
            );
            for (EntryDoor entryDoor : entryDoors) {
                String productImageName = entryDoorService.getImgPathByName(entryDoor.getName());
                entryDoor.setImagePath(imageService.getImgUrl(productImageName));
            }
            model.addAttribute("entryDoors", entryDoors);
            model.addAttribute("sortBy", sortBy);
            model.addAttribute("order", order);
            model.addAttribute("fabric", fabric);
            model.addAttribute("metal", metal);
            model.addAttribute("color", color);
            model.addAttribute("filling", filling);
            return "entry_doors";
    }

    @GetMapping("/entry-doors/{id}")
    public String getEntryDoorById(@PathVariable Long id, Model model) {
        EntryDoor entryDoor = entryDoorService.getEntryDoorById(id);
        String productImageName = entryDoorService.getImgPathByName(entryDoor.getName());
        entryDoor.setImagePath(imageService.getImgUrl(productImageName));
        model.addAttribute("entryDoor", entryDoor);
        return "entry_door_detail";
    }

    @GetMapping("/room-doors")
    public String getRoomDoors(
            @RequestParam(required = false, defaultValue = "name") String sortBy,
            @RequestParam(required = false, defaultValue = "asc") String order,
            @RequestParam(required = false) String fabric,
            @RequestParam(required = false) String facing,
            @RequestParam(required = false) String filling,
            Model model) {

        List<RoomDoor> roomDoors = roomDoorService.getFilteredRoomDoors(sortBy, order, fabric, facing, filling);
        for (RoomDoor roomDoor : roomDoors) {
            String productImageName = roomDoorService.getImgPathByName(roomDoor.getName());
            roomDoor.setImagePath(imageService.getImgUrl(productImageName));
        }
        model.addAttribute("roomDoors", roomDoors);
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("order", order);
        model.addAttribute("fabric", fabric);
        model.addAttribute("facing", facing);
        model.addAttribute("filling", filling);
        return "room_doors";
    }

    @GetMapping("/room-doors/{id}")
    public String getRoomDoorById(@PathVariable Long id, Model model) {
        RoomDoor roomDoor = roomDoorService.getRoomDoorById(id);
        String productImageName = roomDoorService.getImgPathByName(roomDoor.getName());
        roomDoor.setImagePath(imageService.getImgUrl(productImageName));
        model.addAttribute("roomDoor", roomDoor);
        return "room_door_detail";
    }
}