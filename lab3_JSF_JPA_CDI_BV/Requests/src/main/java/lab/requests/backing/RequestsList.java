package lab.requests.backing;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.faces.component.html.HtmlDataTable;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.Size;
import lab.requests.data.RequestRepository;
import lab.requests.entities.Request;

import java.time.LocalDate;
import java.util.List;

@ApplicationScoped
@Named
public class RequestsList {
    @Inject
    private RequestRepository requestRepository;

    @Size(min = 3, max = 60, message = "{request.size}")
    private String newRequest;
    private HtmlDataTable requestsDataTable;

    public String getNewRequest() {
        return newRequest;
    }

    public void setNewRequest(String newRequest) {
        this.newRequest = newRequest;
    }

    public HtmlDataTable getRequestsDataTable() {
        return requestsDataTable;
    }

    public void setRequestsDataTable(HtmlDataTable requestsDataTable) {
        this.requestsDataTable = requestsDataTable;
    }


    public List<Request> getAllRequests() {
        return requestRepository.findAll();
    }

    @Transactional
    public String addRequest() {
        Request request = new Request();
        request.setRequestText(newRequest);
        request.setRequestDate(LocalDate.now());
        requestRepository.create(request);
        return "requestsList.xhtml?faces-redirect=true";
    }

    @Transactional
    public String deleteRequest() {
        Request req = (Request) getRequestsDataTable().getRowData();
        requestRepository.remove(req);
        return "requestsList.xhtml?faces-redirect=true";
    }
}
