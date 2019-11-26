package hallgato;


import com.fasterxml.jackson.databind.JsonMappingException;
import dao.DAOJSON;
import dao.DuplikaltHallgato;
import dao.HallgatoNincs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import webprog.exceptions.InvalidNeptunKod;
import webprog.model.Allapot;
import webprog.model.Hallgato;
import webprog.model.Orak;
import webprog.model.Szak;
import webprog.model.Tipus;

import java.io.IOException;

@Controller
public class HallgatoController {

    @Autowired
    DAOJSON dao;

    @RequestMapping(value = "/")
    public ModelAndView index(){
        ModelAndView mav = new ModelAndView("index");
        mav.addObject("message","Szia");
        return mav;

    }

    @RequestMapping(value = "/addHallgato", method = RequestMethod.GET)
    public ModelAndView addHallgatoForm(){
        ModelAndView mav = new ModelAndView("hallgatoForm", "command", new Hallgato());
        mav.addObject("orakTipusa", Orak.values());
        mav.addObject("allapotok", Allapot.values());
        mav.addObject("tipus", Tipus.values());
        mav.addObject("szak", Szak.values());
        return mav;
    }

    @RequestMapping(value = "/addHallgato", method = RequestMethod.POST)
    public ModelAndView addHallgato(@ModelAttribute Hallgato hallgato) throws IOException {
        try{
            dao.addHallgato(hallgato);
        }
        catch (DuplikaltHallgato duplikaltHallgato){
            ModelAndView mav = new ModelAndView("hallgatoForm", "command", hallgato);
            mav.addObject("orakTipusa", Orak.values());
            mav.addObject("allapotok", Allapot.values());
            mav.addObject("tipusok", Tipus.values());
            mav.addObject("szak", Szak.values());
            mav.addObject("message", "Foglalt a neptunkód: "+ hallgato.getNeptunKod());
            return mav;
        }

        ModelAndView mav = new ModelAndView("redirect:hallgato/"+hallgato.getNeptunKod());
        return mav;
    }

    @RequestMapping(value = "/hallgatok")
    public ModelAndView listHallgatok() throws IOException{
        ModelAndView mav = new ModelAndView("hallgatok");
        mav.addObject("hallgatok", dao.readAllHallgato());
        return mav;
    }

    @RequestMapping(value = "hallgato/{neptunKod}")
    public @ResponseBody ModelAndView getHallgatoByNeptunKod(@PathVariable String neptunKod) throws IOException, HallgatoNincs {
        ModelAndView mav = new ModelAndView("hallgatoDetails", "commad", neptunKod);
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