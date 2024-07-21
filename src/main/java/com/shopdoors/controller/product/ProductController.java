package com.shopdoors.controller.product;

import com.shopdoors.dao.entity.abstracted.Hinge;
import com.shopdoors.dao.entity.product.door.EntryDoor;
import com.shopdoors.dao.entity.product.door.RoomDoor;
import com.shopdoors.dao.entity.product.furniture.Handle;
import com.shopdoors.dao.entity.product.molding.Baseboard;
import com.shopdoors.service.ImageService;
import com.shopdoors.service.product.BaseboardService;
import com.shopdoors.service.product.EntryDoorService;
import com.shopdoors.service.product.HandleService;
import com.shopdoors.service.product.HingeService;
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
public class ProductController {

    private final EntryDoorService entryDoorService;
    private final RoomDoorService roomDoorService;
    private final HandleService handleService;
    private final HingeService hingeService;
    private final BaseboardService baseboardService;
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
        model.addAttribute("roomDoor", roomDoor);
        return "room_door_detail";
    }

    @GetMapping("/furniture")
    public String getFurniture() {
        return "furniture";
    }

    @GetMapping("/handles")
    public String getHandles(
            @RequestParam(required = false, defaultValue = "name") String sortBy,
            @RequestParam(required = false, defaultValue = "asc") String order,
            @RequestParam(required = false) String fabric,
            @RequestParam(required = false) String metal,
            @RequestParam(required = false) String coating,
            @RequestParam(required = false) String socket,
            @RequestParam(required = false) Integer rodLength,
            Model model) {

        List<Handle> handles = handleService.getFilteredHandles(
                sortBy, order, fabric, metal, coating, socket, rodLength
        );
        for (Handle handle : handles) {
            String productImageName = handleService.getImgPathByName(handle.getName());
            handle.setImagePath(imageService.getImgUrl(productImageName));
        }
        model.addAttribute("handles", handles);
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("order", order);
        model.addAttribute("fabric", fabric);
        model.addAttribute("metal", metal);
        model.addAttribute("coating", coating);
        model.addAttribute("socket", socket);
        model.addAttribute("rodLength", rodLength);
        return "handles";
    }

    @GetMapping("/handles/{id}")
    public String getHandleById(@PathVariable Long id, Model model) {
        Handle handle = handleService.getHandleById(id);
        model.addAttribute("handle", handle);
        return "handle_detail";
    }

    @GetMapping("/hinges")
    public String getHinges(
            @RequestParam(required = false, defaultValue = "name") String sortBy,
            @RequestParam(required = false, defaultValue = "asc") String order,
            @RequestParam(required = false) String fabric,
            @RequestParam(required = false) String metal,
            @RequestParam(required = false) String coating,
            @RequestParam(required = false) Integer count,
            @RequestParam(required = false) Boolean isHide,
            Model model) {

        List<Hinge> hinges = hingeService.getFilteredHinges(sortBy, order, fabric, metal, coating, count, isHide);
        for (Hinge hinge : hinges) {
            String productImageName = hingeService.getImgPathByName(hinge.getName());
            hinge.setImagePath(imageService.getImgUrl(productImageName));
        }
        model.addAttribute("hinges", hinges);
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("order", order);
        model.addAttribute("fabric", fabric);
        model.addAttribute("metal", metal);
        model.addAttribute("coating", coating);
        model.addAttribute("count", count);
        model.addAttribute("isHide", isHide);
        return "hinges";
    }

    @GetMapping("/hinges/{id}")
    public String getHingeById(@PathVariable Long id, Model model) {
        Hinge hinge = hingeService.getHingeById(id);
        model.addAttribute("hinge", hinge);
        return "hinge_detail";
    }

    @GetMapping("/moldings")
    public String getMoldings(Model model) {
        List<Baseboard> baseboards = baseboardService.getAllBaseboards();
        model.addAttribute("baseboards", baseboards);
        return "baseboards";
    }

    @GetMapping("/moldings/{id}")
    public String getMoldingById(@PathVariable Long id, Model model) {
        Baseboard baseboard = baseboardService.getBaseboardById(id);
        model.addAttribute("baseboard", baseboard);
        return "baseboard_detail";
    }
}