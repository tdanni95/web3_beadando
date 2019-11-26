package Hallgato;

import com.fasterxml.jackson.databind.JsonMappingException;
import dao.DAO_JSON;
import dao.DuplikaltHallgato;
import dao.HallgatoNincs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import webprog.model.Allapot;
import webprog.model.Orak;
import webprog.model.Tipus;
import webprog.model.Hallgato;
import java.io.IOException;


public class HallgatoController {
    @Autowired
    DAO_JSON dao;

    @RequestMapping(value = "/")
    public ModelAndView index(){
        ModelAndView mav = new ModelAndView("index");
        mav.addObject("message", "hello");
        return mav;
    }

    @RequestMapping(value = "/addHallgato", method = RequestMethod.GET)
    public ModelAndView addHallgatoForm(){
        ModelAndView mav = new ModelAndView("hallgatoForm", "command", new Hallgato());
        mav.addObject("orakTipusa", Orak.values());
        mav.addObject("allapotok", Allapot.values());
        mav.addObject("tipus", Tipus.values());
        return mav;
    }

    @RequestMapping(value = "/addKocsi", method = RequestMethod.POST)
    public ModelAndView addHallgato(@ModelAttribute Hallgato hallgato) throws IOException{
        try{
            dao.addHallgato(hallgato);
        }
        catch (DuplikaltHallgato duplikaltHallgato){
            ModelAndView mav = new ModelAndView("hallgatoForm", "command", hallgato);
            mav.addObject("orakTipusa", Orak.values());
            mav.addObject("allapotok", Allapot.values());
            mav.addObject("tipus", Tipus.values());
            mav.addObject("message", "Foglalt a neptunkód"+ hallgato.getNeptunKod());
            return mav;
        }

        ModelAndView mav = new ModelAndView("redirect:hallgato/"+hallgato.getNeptunKod());
        return mav;
    }

    @RequestMapping(value = "hallgatok")
    public ModelAndView listHallgatok() throws IOException{
        ModelAndView mav = new ModelAndView("hallgatok");
        mav.addObject("hallgatok", dao.readAllHallgato());
        return mav;
    }

    @RequestMapping(value = "hallgato/{neptunkod}")
    public ModelAndView getHallgatoByNeptunKod(@PathVariable String neptunKod) throws IOException, HallgatoNincs{
        ModelAndView mav = new ModelAndView("hallgatoDetails");
        mav.addObject("hallgato", dao.readByNeptunKod(neptunKod));
        return mav;
    }

    @ExceptionHandler(HallgatoNincs.class)
    public ModelAndView errorHallgatoNincs(){
        ModelAndView mav = new ModelAndView("index");
        mav.addObject("message", "A kért hallgató nem található");
        return mav;
    }

    @ExceptionHandler(JsonMappingException.class)
    public String error(Exception e){
        System.out.println(e.getMessage());
        return "";
    }
}
