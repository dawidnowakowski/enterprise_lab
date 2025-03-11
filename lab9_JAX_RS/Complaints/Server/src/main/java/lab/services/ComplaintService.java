package lab.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import lab.data.ComplaintRepository;
import lab.dto.ComplaintDTO;
import lab.entities.Complaint;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import java.util.List;
import java.lang.reflect.Type;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class ComplaintService {
    @Inject
    ComplaintRepository complaintRepository;

    @Inject
    EntityManager em;

    @Transactional
    public void create(ComplaintDTO dto) {
        ModelMapper mapper = new ModelMapper();
        complaintRepository.create(mapper.map(dto, Complaint.class));
    }

    @Transactional
    public void edit(ComplaintDTO dto) {
        ModelMapper mapper = new ModelMapper();
        complaintRepository.edit(mapper.map(dto, Complaint.class));
    }

    @Transactional
    public void remove(ComplaintDTO dto) {
        ModelMapper mapper = new ModelMapper();
        complaintRepository.remove(mapper.map(dto, Complaint.class));
    }

    public ComplaintDTO find(Long id) {
        ModelMapper mapper = new ModelMapper();
        return mapper.map(complaintRepository.find(id), ComplaintDTO.class);
    }

    public List<ComplaintDTO> findAll(String status) {

        if (status != null && !"".equals(status))
            return em.createNamedQuery("Complaint.findByStatus")
                    .setParameter("status", status)
                    .getResultList();
        else {
            ModelMapper mapper = new ModelMapper();
            List<Complaint> entityList = complaintRepository.findAll();
            Type listType =
                    new TypeToken<List<ComplaintDTO>>() {}.getType();
            List<ComplaintDTO> dtoList =
                    mapper.map(entityList, listType);
            return dtoList;
        }


    }
}
