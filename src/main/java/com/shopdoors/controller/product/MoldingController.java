package com.shopdoors.controller.product;

import com.shopdoors.dao.entity.product.molding.AdditionalElement;
import com.shopdoors.dao.entity.product.molding.Baseboard;
import com.shopdoors.dao.entity.product.molding.FloorCovering;
import com.shopdoors.dao.entity.product.molding.Jamb;
import com.shopdoors.dao.entity.product.molding.Panel;
import com.shopdoors.dao.entity.product.molding.Pannier;
import com.shopdoors.service.ImageService;
import com.shopdoors.service.product.AdditionalElementService;
import com.shopdoors.service.product.BaseboardService;
import com.shopdoors.service.product.FloorCoveringService;
import com.shopdoors.service.product.JambService;
import com.shopdoors.service.product.PanelService;
import com.shopdoors.service.product.PannierService;
import com.shopdoors.service.user.AuthorizeUserDetailsService;
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
@RequestMapping(path = "/moldings")
public class MoldingController {

    private final BaseboardService baseboardService;
    private final ImageService imageService;
    private final AdditionalElementService additionalElementService;
    private final FloorCoveringService floorCoveringService;
    private final JambService jambService;
    private final PanelService panelService;
    private final PannierService pannierService;
    private final AuthorizeUserDetailsService userService;

    @GetMapping("")
    public String getMoldings(Model model) {
        model.addAttribute("imgProfileUrl", userService.getCurrentUserImgPath());
        return "products/moldings";
    }

    @GetMapping("/baseboards")
    public String getBaseboards(
            @RequestParam(required = false, defaultValue = "name") String sortBy,
            @RequestParam(required = false, defaultValue = "asc") String order,
            @RequestParam(required = false) String fabric,
            @RequestParam(required = false) String filling,
            @RequestParam(required = false) String facing,
            Model model) {

        List<Baseboard> baseboards = baseboardService.getFilteredBaseboards(sortBy, order, fabric, filling, facing);
        for (Baseboard baseboard : baseboards) {
            String productImageName = baseboardService.getImgPathByName(baseboard.getName());
            baseboard.setImagePath(imageService.getImgUrl(productImageName));
        }
        model.addAttribute("imgProfileUrl", userService.getCurrentUserImgPath());
        model.addAttribute("baseboards", baseboards);
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("order", order);
        model.addAttribute("fabric", fabric);
        model.addAttribute("filling", filling);
        model.addAttribute("facing", facing);
        return "products/moldings/baseboards";
    }

    @GetMapping("/baseboards/{id}")
    public String getBaseboardById(@PathVariable UUID id, Model model) {
        Baseboard baseboard = baseboardService.getBaseboardById(id);
        String productImageName = baseboardService.getImgPathByName(baseboard.getName());
        baseboard.setImagePath(imageService.getImgUrl(productImageName));
        model.addAttribute("imgProfileUrl", userService.getCurrentUserImgPath());
        model.addAttribute("baseboard", baseboard);
        return "products/moldings/baseboard_detail";
    }

    @GetMapping("/additional-elements")
    public String getAdditionalElements(
            @RequestParam(required = false, defaultValue = "name") String sortBy,
            @RequestParam(required = false, defaultValue = "asc") String order,
            @RequestParam(required = false) String fabric,
            @RequestParam(required = false) String filling,
            @RequestParam(required = false) String facing,
            @RequestParam(required = false) String moldingType,
            Model model) {

        List<AdditionalElement> additionalElements = additionalElementService.getFilteredAdditionalElement(
                sortBy, order, fabric, filling, facing, moldingType
        );
        for (AdditionalElement additionalElement : additionalElements) {
            String productImageName = additionalElementService.getImgPathByName(additionalElement.getName());
            additionalElement.setImagePath(imageService.getImgUrl(productImageName));
        }
        model.addAttribute("imgProfileUrl", userService.getCurrentUserImgPath());
        model.addAttribute("additionalElements", additionalElements);
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("order", order);
        model.addAttribute("fabric", fabric);
        model.addAttribute("filling", filling);
        model.addAttribute("facing", facing);
        model.addAttribute("moldingType", moldingType);
        return "products/moldings/additional_elements";
    }

    @GetMapping("/additional-elements/{id}")
    public String getAdditionalElementById(@PathVariable UUID id, Model model) {
        AdditionalElement additionalElement = additionalElementService.getAdditionalElementById(id);
        String productImageName = additionalElementService.getImgPathByName(additionalElement.getName());
        additionalElement.setImagePath(imageService.getImgUrl(productImageName));
        model.addAttribute("imgProfileUrl", userService.getCurrentUserImgPath());
        model.addAttribute("additionalElement", additionalElement);
        return "products/moldings/additional_element_detail";
    }

    @GetMapping("/floor-coverings")
    public String getFloorCoverings(
            @RequestParam(required = false, defaultValue = "name") String sortBy,
            @RequestParam(required = false, defaultValue = "asc") String order,
            @RequestParam(required = false) String fabric,
            @RequestParam(required = false) String filling,
            @RequestParam(required = false) String facing,
            @RequestParam(required = false) String waterResistanceType,
            Model model) {

        List<FloorCovering> floorCoverings = floorCoveringService.getFilteredFloorCoverings(
                sortBy, order, fabric, filling, facing, waterResistanceType
        );
        for (FloorCovering floorCovering : floorCoverings) {
            String productImageName = floorCoveringService.getImgPathByName(floorCovering.getName());
            floorCovering.setImagePath(imageService.getImgUrl(productImageName));
        }
        model.addAttribute("imgProfileUrl", userService.getCurrentUserImgPath());
        model.addAttribute("floorCoverings", floorCoverings);
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("order", order);
        model.addAttribute("fabric", fabric);
        model.addAttribute("filling", filling);
        model.addAttribute("facing", facing);
        model.addAttribute("waterResistanceType", waterResistanceType);
        return "products/moldings/floor_coverings";
    }

    @GetMapping("/floor-coverings/{id}")
    public String getFloorCoveringById(@PathVariable UUID id, Model model) {
        FloorCovering floorCovering = floorCoveringService.getFloorCoveringById(id);
        String productImageName = floorCoveringService.getImgPathByName(floorCovering.getName());
        floorCovering.setImagePath(imageService.getImgUrl(productImageName));
        model.addAttribute("imgProfileUrl", userService.getCurrentUserImgPath());
        model.addAttribute("floorCovering", floorCovering);
        return "products/moldings/floor_covering_detail";
    }

    @GetMapping("/jambs")
    public String getJambs(
            @RequestParam(required = false, defaultValue = "name") String sortBy,
            @RequestParam(required = false, defaultValue = "asc") String order,
            @RequestParam(required = false) String fabric,
            @RequestParam(required = false) String filling,
            @RequestParam(required = false) String facing,
            @RequestParam(required = false) String moldingType,
            Model model) {

        List<Jamb> jambs = jambService.getFilteredJamb(
                sortBy, order, fabric, filling, facing, moldingType
        );
        for (Jamb jamb : jambs) {
            String productImageName = jambService.getImgPathByName(jamb.getName());
            jamb.setImagePath(imageService.getImgUrl(productImageName));
        }
        model.addAttribute("imgProfileUrl", userService.getCurrentUserImgPath());
        model.addAttribute("jambs", jambs);
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("order", order);
        model.addAttribute("fabric", fabric);
        model.addAttribute("filling", filling);
        model.addAttribute("facing", facing);
        model.addAttribute("moldingType", moldingType);
        return "products/moldings/jambs";
    }

    @GetMapping("/jambs/{id}")
    public String getJambsById(@PathVariable UUID id, Model model) {
        Jamb jamb = jambService.getJambById(id);
        String productImageName = jambService.getImgPathByName(jamb.getName());
        jamb.setImagePath(imageService.getImgUrl(productImageName));
        model.addAttribute("imgProfileUrl", userService.getCurrentUserImgPath());
        model.addAttribute("jamb", jamb);
        return "products/moldings/jamb_detail";
    }

    @GetMapping("/panels")
    public String getPanels(
            @RequestParam(required = false, defaultValue = "name") String sortBy,
            @RequestParam(required = false, defaultValue = "asc") String order,
            @RequestParam(required = false) String fabric,
            @RequestParam(required = false) String filling,
            @RequestParam(required = false) String facing,
            @RequestParam(required = false) String panelType,
            Model model) {

        List<Panel> panels = panelService.getFilteredPanel(
                sortBy, order, fabric, filling, facing, panelType
        );
        for (Panel panel : panels) {
            String productImageName = panelService.getImgPathByName(panel.getName());
            panel.setImagePath(imageService.getImgUrl(productImageName));
        }
        model.addAttribute("imgProfileUrl", userService.getCurrentUserImgPath());
        model.addAttribute("panels", panels);
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("order", order);
        model.addAttribute("fabric", fabric);
        model.addAttribute("filling", filling);
        model.addAttribute("facing", facing);
        model.addAttribute("panelType", panelType);
        return "products/moldings/panels";
    }

    @GetMapping("/panels/{id}")
    public String getPanelById(@PathVariable UUID id, Model model) {
        Panel panel = panelService.getPanelById(id);
        String productImageName = panelService.getImgPathByName(panel.getName());
        panel.setImagePath(imageService.getImgUrl(productImageName));
        model.addAttribute("imgProfileUrl", userService.getCurrentUserImgPath());
        model.addAttribute("panel", panel);
        return "products/moldings/panel_detail";
    }

    @GetMapping("/panniers")
    public String getPannier(
            @RequestParam(required = false, defaultValue = "name") String sortBy,
            @RequestParam(required = false, defaultValue = "asc") String order,
            @RequestParam(required = false) String fabric,
            @RequestParam(required = false) String filling,
            @RequestParam(required = false) String facing,
            @RequestParam(required = false) String moldingType,
            Model model) {

        List<Pannier> panniers = pannierService.getFilteredPannier(
                sortBy, order, fabric, filling, facing, moldingType
        );
        for (Pannier pannier : panniers) {
            String productImageName = pannierService.getImgPathByName(pannier.getName());
            pannier.setImagePath(imageService.getImgUrl(productImageName));
        }
        model.addAttribute("imgProfileUrl", userService.getCurrentUserImgPath());
        model.addAttribute("panniers", panniers);
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("order", order);
        model.addAttribute("fabric", fabric);
        model.addAttribute("filling", filling);
        model.addAttribute("facing", facing);
        model.addAttribute("moldingType", moldingType);
        return "products/moldings/panniers";
    }

    @GetMapping("/panniers/{id}")
    public String getPannierById(@PathVariable UUID id, Model model) {
        Pannier pannier = pannierService.getPannierById(id);
        String productImageName = pannierService.getImgPathByName(pannier.getName());
        pannier.setImagePath(imageService.getImgUrl(productImageName));
        model.addAttribute("imgProfileUrl", userService.getCurrentUserImgPath());
        model.addAttribute("pannier", pannier);
        return "products/moldings/pannier_detail";
    }
}