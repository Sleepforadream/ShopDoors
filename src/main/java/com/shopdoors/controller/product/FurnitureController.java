package com.shopdoors.controller.product;

import com.shopdoors.dao.entity.product.furniture.EntryLock;
import com.shopdoors.dao.entity.product.furniture.Fastening;
import com.shopdoors.dao.entity.product.furniture.Handle;
import com.shopdoors.dao.entity.product.furniture.Peephole;
import com.shopdoors.dao.entity.product.furniture.Retainer;
import com.shopdoors.dao.entity.product.furniture.RoomHinge;
import com.shopdoors.dao.entity.product.furniture.RoomLock;
import com.shopdoors.service.ImageService;
import com.shopdoors.service.product.EntryLockService;
import com.shopdoors.service.product.FasteningService;
import com.shopdoors.service.product.HandleService;
import com.shopdoors.service.product.PeepholeService;
import com.shopdoors.service.product.RetainerService;
import com.shopdoors.service.product.RoomHingeService;
import com.shopdoors.service.product.RoomLockService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
@RequestMapping(path = "/furniture")
public class FurnitureController {

    private final HandleService handleService;
    private final RoomHingeService roomHingeService;
    private final ImageService imageService;
    private final RoomLockService roomLockService;
    private final EntryLockService entryLockService;
    private final RetainerService retainerService;
    private final PeepholeService peepholeService;
    private final FasteningService fasteningService;

    @GetMapping("")
    public String getFurniture() {
        return "products/furniture";
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
        return "products/furniture/handles";
    }

    @GetMapping("/handles/{id}")
    public String getHandleById(@PathVariable UUID id, Model model) {
        Handle handle = handleService.getHandleById(id);
        String productImageName = handleService.getImgPathByName(handle.getName());
        handle.setImagePath(imageService.getImgUrl(productImageName));
        model.addAttribute("handle", handle);
        return "products/furniture/handle_detail";
    }

    @GetMapping("/room-hinges")
    public String getHinges(
            @RequestParam(required = false, defaultValue = "name") String sortBy,
            @RequestParam(required = false, defaultValue = "asc") String order,
            @RequestParam(required = false) String fabric,
            @RequestParam(required = false) String metal,
            @RequestParam(required = false) String coating,
            @RequestParam(required = false) Integer count,
            @RequestParam(required = false) String hingeType,
            Model model) {

        List<RoomHinge> hinges = roomHingeService.getFilteredHinges(sortBy, order, fabric, metal, coating, count, hingeType);
        for (RoomHinge hinge : hinges) {
            String productImageName = roomHingeService.getImgPathByName(hinge.getName());
            hinge.setImagePath(imageService.getImgUrl(productImageName));
        }
        model.addAttribute("hinges", hinges);
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("order", order);
        model.addAttribute("fabric", fabric);
        model.addAttribute("metal", metal);
        model.addAttribute("coating", coating);
        model.addAttribute("count", count);
        model.addAttribute("hingeType", hingeType);
        return "products/furniture/room_hinges";
    }

    @GetMapping("/room-hinges/{id}")
    public String getHingeById(@PathVariable UUID id, Model model) {
        RoomHinge roomHinge = roomHingeService.getHingeById(id);
        String productImageName = roomHingeService.getImgPathByName(roomHinge.getName());
        roomHinge.setImagePath(imageService.getImgUrl(productImageName));
        model.addAttribute("roomHinge", roomHinge);
        return "products/furniture/room_hinge_detail";
    }

    @GetMapping("/room-locks")
    public String getRoomLocks(
            @RequestParam(required = false, defaultValue = "name") String sortBy,
            @RequestParam(required = false, defaultValue = "asc") String order,
            @RequestParam(required = false) String fabric,
            @RequestParam(required = false) String metal,
            @RequestParam(required = false) String coating,
            @RequestParam(required = false) String tongueType,
            @RequestParam(required = false) String lockType,
            Model model) {

        List<RoomLock> roomLocks = roomLockService.getFilteredRoomLocks(
                sortBy, order, fabric, metal, coating, tongueType, lockType
        );
        for (RoomLock roomLock : roomLocks) {
            String productImageName = roomLockService.getImgPathByName(roomLock.getName());
            roomLock.setImagePath(imageService.getImgUrl(productImageName));
        }
        model.addAttribute("roomLocks", roomLocks);
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("order", order);
        model.addAttribute("fabric", fabric);
        model.addAttribute("metal", metal);
        model.addAttribute("coating", coating);
        model.addAttribute("tongueType", tongueType);
        model.addAttribute("lockType", lockType);
        return "products/furniture/room-locks";
    }

    @GetMapping("/room-locks/{id}")
    public String getRoomLockById(@PathVariable UUID id, Model model) {
        RoomLock roomLock = roomLockService.getRoomLockById(id);
        String productImageName = roomLockService.getImgPathByName(roomLock.getName());
        roomLock.setImagePath(imageService.getImgUrl(productImageName));
        model.addAttribute("roomLock", roomLock);
        return "products/furniture/room_lock_detail";
    }

    @GetMapping("/entry-locks")
    public String getEntryLocks(
            @RequestParam(required = false, defaultValue = "name") String sortBy,
            @RequestParam(required = false, defaultValue = "asc") String order,
            @RequestParam(required = false) String fabric,
            @RequestParam(required = false) String metal,
            @RequestParam(required = false) String coating,
            @RequestParam(required = false) String defenseClass,
            @RequestParam(required = false) String firstKeyType,
            @RequestParam(required = false) String secondKeyType,
            Model model) {

        List<EntryLock> entryLocks = entryLockService.getFilteredEntryLocks(
                sortBy, order, fabric, metal, coating, defenseClass, firstKeyType, secondKeyType
        );
        for (EntryLock entryLock : entryLocks) {
            String productImageName = entryLockService.getImgPathByName(entryLock.getName());
            entryLock.setImagePath(imageService.getImgUrl(productImageName));
        }
        model.addAttribute("entryLocks", entryLocks);
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("order", order);
        model.addAttribute("fabric", fabric);
        model.addAttribute("metal", metal);
        model.addAttribute("coating", coating);
        model.addAttribute("defenseClass", defenseClass);
        model.addAttribute("firstKeyType", firstKeyType);
        model.addAttribute("secondKeyType", secondKeyType);
        return "products/furniture/entry-locks";
    }

    @GetMapping("/entry-locks/{id}")
    public String getEntryLockById(@PathVariable UUID id, Model model) {
        EntryLock entryLock = entryLockService.getEntryLockById(id);
        String productImageName = entryLockService.getImgPathByName(entryLock.getName());
        entryLock.setImagePath(imageService.getImgUrl(productImageName));
        model.addAttribute("entryLock", entryLock);
        return "products/furniture/entry_lock_detail";
    }

    @GetMapping("/retainers")
    public String getRetainers(
            @RequestParam(required = false, defaultValue = "name") String sortBy,
            @RequestParam(required = false, defaultValue = "asc") String order,
            @RequestParam(required = false) String fabric,
            @RequestParam(required = false) String metal,
            @RequestParam(required = false) String coating,
            @RequestParam(required = false) String socket,
            @RequestParam(required = false) String keyRetainer,
            Model model) {

        List<Retainer> retainers = retainerService.getFilteredRetainers(
                sortBy, order, fabric, metal, coating, socket, keyRetainer
        );
        for (Retainer retainer : retainers) {
            String productImageName = retainerService.getImgPathByName(retainer.getName());
            retainer.setImagePath(imageService.getImgUrl(productImageName));
        }
        model.addAttribute("retainers", retainers);
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("order", order);
        model.addAttribute("fabric", fabric);
        model.addAttribute("metal", metal);
        model.addAttribute("coating", coating);
        model.addAttribute("socket", socket);
        model.addAttribute("keyRetainer", keyRetainer);
        return "products/furniture/retainers";
    }

    @GetMapping("/retainers/{id}")
    public String getRetainerById(@PathVariable UUID id, Model model) {
        Retainer retainer = retainerService.getRetainerById(id);
        String productImageName = retainerService.getImgPathByName(retainer.getName());
        retainer.setImagePath(imageService.getImgUrl(productImageName));
        model.addAttribute("retainer", retainer);
        return "products/furniture/retainer_detail";
    }

    @GetMapping("/peepholes")
    public String getPeepholes(
            @RequestParam(required = false, defaultValue = "name") String sortBy,
            @RequestParam(required = false, defaultValue = "asc") String order,
            @RequestParam(required = false) String fabric,
            @RequestParam(required = false) String metal,
            @RequestParam(required = false) String coating,
            @RequestParam(required = false) Integer minimumDepth,
            @RequestParam(required = false) Integer maximumDepth,
            @RequestParam(required = false) String peepholeType,
            Model model) {

        List<Peephole> peepholes = peepholeService.getFilteredPeepholes(
                sortBy, order, fabric, metal, coating, minimumDepth, maximumDepth, peepholeType
        );
        for (Peephole peephole : peepholes) {
            String productImageName = peepholeService.getImgPathByName(peephole.getName());
            peephole.setImagePath(imageService.getImgUrl(productImageName));
        }
        model.addAttribute("peepholes", peepholes);
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("order", order);
        model.addAttribute("fabric", fabric);
        model.addAttribute("metal", metal);
        model.addAttribute("coating", coating);
        model.addAttribute("minimumDepth", minimumDepth);
        model.addAttribute("maximumDepth", maximumDepth);
        model.addAttribute("peepholeType", peepholeType);
        return "products/furniture/peepholes";
    }

    @GetMapping("/peepholes/{id}")
    public String getPeepholeById(@PathVariable UUID id, Model model) {
        Peephole peephole = peepholeService.getPeepholeById(id);
        String productImageName = peepholeService.getImgPathByName(peephole.getName());
        peephole.setImagePath(imageService.getImgUrl(productImageName));
        model.addAttribute("peephole", peephole);
        return "products/furniture/peephole_detail";
    }

    @GetMapping("/fastenings")
    public String getFastenings(
            @RequestParam(required = false, defaultValue = "name") String sortBy,
            @RequestParam(required = false, defaultValue = "asc") String order,
            @RequestParam(required = false) String fabric,
            @RequestParam(required = false) String metal,
            @RequestParam(required = false) String coating,
            Model model) {

        List<Fastening> fastenings = fasteningService.getFilteredFastenings(sortBy, order, fabric, metal, coating);
        for (Fastening fastening : fastenings) {
            String productImageName = fasteningService.getImgPathByName(fastening.getName());
            fastening.setImagePath(imageService.getImgUrl(productImageName));
        }
        model.addAttribute("fastenings", fastenings);
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("order", order);
        model.addAttribute("fabric", fabric);
        model.addAttribute("metal", metal);
        model.addAttribute("coating", coating);
        return "products/furniture/fastenings";
    }

    @GetMapping("/fastenings/{id}")
    public String getFasteningById(@PathVariable UUID id, Model model) {
        Fastening fastening = fasteningService.getFasteningById(id);
        String productImageName = fasteningService.getImgPathByName(fastening.getName());
        fastening.setImagePath(imageService.getImgUrl(productImageName));
        model.addAttribute("fastening", fastening);
        return "products/furniture/fastening_detail";
    }
}