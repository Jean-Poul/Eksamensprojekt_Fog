package PresentationLayer;

import FunctionLayer.LogicFacade;

import FunctionLayer.Exceptions.LoginSampleException;
import FunctionLayer.Tables.ItemList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Class that gets a List of all items from database and sets attribute for view on adminItemList.jsp
 *
 * @author Alexander Pihl, Mick Larsen, Morten Rahbek, Per Kringelbach, Jean-Poul Leth-Møller
 */
public class AdminItemList extends Command {

    /**
     * Get Item list from database and set attribute for jsp page
     *
     * @param request request for Http Servlet
     * @param response response for Http Servlet
     * @return adminItemList
     * @throws LoginSampleException LoginSampleException
     */
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
