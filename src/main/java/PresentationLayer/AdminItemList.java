package PresentationLayer;


import FunctionLayer.LogicFacade;

import FunctionLayer.Exceptions.LoginSampleException;
import FunctionLayer.Tables.ItemList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class AdminItemList extends Command {
    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws LoginSampleException {
        // Initializing session variable with current session
        HttpSession session = request.getSession();


        // Initializing List with ItemList object
        List<ItemList> itemList = (List<ItemList>) session.getAttribute("itemList");


        // Singleton to initialize an instance of UserProposition
        // if List is empty
        if (itemList == null) {
            itemList = LogicFacade.getItemListAdmin();
        } else {
            itemList = (List<ItemList>) session.getAttribute("itemList");
        }


        // Attribute to use on jsp site
        request.setAttribute("itemList", itemList);

        return "adminItemList";
    }
}
