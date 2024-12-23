package SmartTourism.Heatmap;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Scanner;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Controller
public class WebController {

    @GetMapping("/")
    public String home() {
        System.out.println("accessing the smart tourism page");
        return "index";
    }

    @GetMapping("/heatmaptesting")
    public String test() {
        System.out.println("testing the heatmap page");
        return "heatmaptesting";
    }
    
    @GetMapping("/heatmap")
    public String testing(Model model) {
    	System.out.println("accessing heatmap page");
    	//settings String to pass to html
    	String settings = new String();
    	//Assign settings the heatmap settings
    	settings = HeatmapApplication.settings;
    	//Pass settings to html
    	model.addAttribute("settings",settings);
    	
    	//artifacts String to pass to html
    	String artifacts = new String();
    	//Artifact reference variable
    	Artifact artifactRef = null;
    	//iterate till the end of artifact list
    	for(int i = 0; i < HeatmapApplication.artifactList.size(); i++) {
    		//assign artifactref to the next artifact in the artifact list
    		artifactRef = HeatmapApplication.artifactList.get(i);
    		//if, ever, we are at the end artifact
    		if(i == HeatmapApplication.artifactList.size()-1) {
    			//format with no comma at the end
    			artifacts+=artifactRef.getName()+","+artifactRef.getX()+","+artifactRef.getY();
    		}
    		//otherwise format with comma at end
    		else {
        		artifacts+=artifactRef.getName()+","+artifactRef.getX()+","+artifactRef.getY()+",";
    		}
    	}
    	//Pass artifacts to html
    	model.addAttribute("artifacts",artifacts);
    	
    	//beacons String to pass to html
    	String becaons = new String();
    	//beacons reference variable
    	Beacons beaconList = null;
    	//iterate till end of beacons list
    	for(int i = 0; i < HeatmapApplication.beaconList.size(); i++) {
    		//assign beaconList as the next beacon in beacons list
    		beaconList = HeatmapApplication.beaconList.get(i);
    		//if ,ever, we are at the end of beaconlist
    		if(i == HeatmapApplication.artifactList.size()-1) {
    			//format with no comma at the end
    			becaons+=beaconList.getBLEID()+","+beaconList.getBLEXC()+","+beaconList.getBLEYC();
    		}
    		//otherwise format with comma at the end
    		else {
        		becaons+=beaconList.getBLEID()+","+beaconList.getBLEXC()+","+beaconList.getBLEYC()+",";
    		}
    	}
    	//Pass beacons to html
    	model.addAttribute("beacons",becaons);
    	
    	//visitors String to pass to html
    	String visitors = new String();
    	//visitors reference variable
    	Visitor visitorsList = null;
    	//iterate until the end of visitorsList
    	for(int i = 0; i < HeatmapApplication.visitorList.size(); i++) {
    		//assign visitorsList as the next visistor
    		visitorsList = HeatmapApplication.visitorList.get(i);
    		//if, ever, we are at the end of visistors list and our visistors x and y values exist
    		if(i == HeatmapApplication.visitorList.size()-1 && visitorsList.getVisitorX() != -1 && visitorsList.getVisitorY()!=-1) {
    			//format with no commas at the end
    			visitors+=visitorsList.getVisitorX()+","+visitorsList.getVisitorY();
    		}
    		//otherwise if we are at the end, and the last x and y values don't exist
    		else if(i == HeatmapApplication.visitorList.size()-1 && visitorsList.getVisitorX() == -1 && visitorsList.getVisitorY() ==-1) {
    			//remove the previous comma at the end
    			visitors = visitors.substring(0,visitors.length()-1);
    		}
    		//otherwise if x and y values are ever -1 outside of the previous choice, then we continue
    		else if(HeatmapApplication.visitorList.get(i).getVisitorX() == -1 && HeatmapApplication.visitorList.get(i).getVisitorY() == -1) {
    			continue;
    		}
    		//otherwise format with commas
    		else {
        		visitors+=visitorsList.getVisitorX()+","+visitorsList.getVisitorY()+",";
    			
    		}
    	}
    	//Pass visitors list to html
    	model.addAttribute("visitors",visitors);
    	
    	return "heatmap";
    }

}