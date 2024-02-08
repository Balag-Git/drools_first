/*
package com.drools.first.service;

import com.drools.first.model.Jewellery;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Jewelservice {

    @Autowired
    private static KieSession kieSession;
    public static void setKieSession(KieSession kieSession) {
        Jewelservice.kieSession = kieSession;
    }

    public static void applyDiscount(Jewellery jewellery) {
        // Insert the customer data into the KieSession
        kieSession.insert(jewellery);

        // Fire all rules in the KieSession
        int rulesFired = kieSession.fireAllRules();
        System.out.println("Number of rules fired: " + rulesFired);

        // The customer object is now updated with the applied discount
        System.out.println("Discount applied: " + jewellery.getDiscount());
    }
}


*/
/*package com.drools.first.service;

import com.drools.first.model.Jewellery;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Jewelservice {

    private KieSession kieSession;
    private boolean ruleFired = false;

    @Autowired
    public Jewelservice(KieSession kieSession) {
        this.kieSession = kieSession;
    }

    public Jewelservice() {

    }

    public void applyDiscount(Jewellery jewellery) {
        // Insert the jewellery data into the KieSession
        kieSession.insert(jewellery);

        // Fire rules in the KieSession
        int rulesFired = kieSession.fireAllRules(1);

        // Check if any rule was fired
        if (rulesFired > 0) {

            System.out.println("Discount applied: " + jewellery.getDiscount());
        } else {

            System.out.println("No discount applied.");
        }

        // Reset the KieSession to stop further rule execution
        kieSession.dispose();
    }




    public KieSession getKieSession() {
        return kieSession;
    }

    public void setKieSession(KieSession kieSession) {
        this.kieSession = kieSession;
    }
}*/
package com.drools.first.service;

import com.drools.first.model.Jewellery;
import com.drools.first.model.Responsejew;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class Jewelservice {

    private KieSession kieSession;


    @Autowired
    public Jewelservice() {
        this.kieSession = kieSession;
    }

    public List<Responsejew> applyDiscount(String jewel) {

        try {
            Jewellery jewellery = new ObjectMapper().readValue(jewel, Jewellery.class);


        // Insert the jewellery data into the KieSession
        kieSession.insert(jewellery);

        List<Responsejew> objects = new ArrayList<>();
        int rulesFired = kieSession.fireAllRules();
        System.out.println(rulesFired);

        Jewellery jewobj = new ObjectMapper().readValue(jewel, Jewellery.class);
       for(int i=1;i<=rulesFired;i++)
       {
         // System.out.println("1   "+jewobj);
           kieSession.insert(jewobj);
           kieSession.fireAllRules(i);
           //System.out.println("2   "+jewobj);
           Responsejew responseobj = new Responsejew();
           responseobj.setDiscount(jewobj.getDiscount());
           responseobj.setBank(jewobj.getBank());
           objects.add(responseobj);
           System.out.println(objects);
       }


       /*System.out.println(objects.size());
        System.out.println(objects);*/

        return objects;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public void setKieSession(KieSession kieSession) {
        this.kieSession = kieSession;
    }

    public KieSession getKieSession() {
        return kieSession;
    }
}

/*            for (Object object : objects) {
            System.out.println("Processing object: " + object);
                if (object instanceof Jewellery) {
                   Jewellery updatedJewellery = (Jewellery) object;
                    System.out.println("Rule applied: " + updatedJewellery.getDiscount());
                }
            }*/
