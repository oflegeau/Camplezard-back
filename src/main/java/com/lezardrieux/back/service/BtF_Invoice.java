package com.lezardrieux.back.service;

import com.lezardrieux.back.back.modelDAO.DAO_Invoice;
import com.lezardrieux.back.back.modelDAO.DAO_InvoiceLine;
import com.lezardrieux.back.back.modelDAO.DAO_Resa;
import com.lezardrieux.back.back.repoDAO.Repo_Invoice;
import com.lezardrieux.back.front.model.Invoice;
import com.lezardrieux.back.front.model.Reponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

import static com.lezardrieux.back.BackApplication.trace;
import static org.hibernate.bytecode.BytecodeLogger.LOGGER;

@Service
public class BtF_Invoice implements DBS_Invoice {

    @Autowired
    Repo_Invoice repo_Invoice;
    @Autowired
    DBS_InvoiceLine dbs_Project_invoiceLine;
    @Autowired
    DBS_Resa dbs_resa;

    //------------------------------------------------------------------------------//

    private DAO_Invoice getBack(Invoice obj) {
        return new DAO_Invoice(obj.getCode().substring(0, Math.min(30, obj.getCode().length())),
                                obj.getLibelle().substring(0, Math.min(255, obj.getLibelle().length())),
                                obj.getStatus(),
                                obj.getCreated());
    }
    
    //------------------------------------------------------------------------------//

    private Invoice get(DAO_Invoice obj) {
        return new Invoice( obj.getId(),
                                    obj.getCode(),
                                    obj.getLibelle(),
                                    obj.getStatus(),
                                    obj.getCreated(),
                                    dbs_Project_invoiceLine.getList(obj.getLines()),
                                    dbs_Project_invoiceLine.getTotalGross(),
                                    dbs_Project_invoiceLine.getTotalVta(),
                                    dbs_Project_invoiceLine.getTotalNet());
    }

    //------------------------------------------------------------------------------//

    @Override
    @Transactional
    public Invoice get(Long id) {
        Optional<DAO_Invoice> option = repo_Invoice.findById(id);
        return option.map(this::get).orElse(null);
    }

    //------------------------------------------------------------------------------//

    @Override
    @Transactional
    public DAO_Invoice getDAO(Long id) {
        Optional<DAO_Invoice> option = repo_Invoice.findById(id);
        return option.orElse(null);
    }

    @Override
    public List<Invoice> getList(List<DAO_Invoice> list) {
        List<Invoice> _List = new ArrayList<>();
        for (DAO_Invoice _TObj : list) {
            _List.add(get(_TObj));
        }
        return _List;
    }

    //------------------------------------------------------------------------------//
    // POST
    //------------------------------------------------------------------------------//

    @Override
    @Transactional
    public Reponse create(String idResa, Invoice obj) {
        try {
            // ----------------------------------------- //
            // sauvegarde sans jointure pour création ID //
            // ----------------------------------------- //
            DAO_Invoice dao_invoice = repo_Invoice.save(getBack(obj));
            // ----------------------------------------- //
            // gestion des jointures                     //
            // ----------------------------------------- //
            DAO_Resa _parent = dbs_resa.getDAO(idResa);
            if (_parent == null) {
                LOGGER.error("DAO_Resa null/ID = " + idResa);
                return null;
            }
            // ----------------------------------------- //
            if (dao_invoice.getCode().equals("")) {
                dao_invoice.setCode(_parent.getId().toString().substring(0, 6));
            }
            dao_invoice = _parent.addInvoice(dao_invoice);
            // ----------------------------------------- //
            dao_invoice = repo_Invoice.save(dao_invoice);
            // ----------------------------------------- //
            if (trace) LOGGER.warn(dao_invoice.toString());
            return new Reponse(HttpStatus.CREATED, dao_invoice.getId().toString(), 0L);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return null;
        }
    }

    //------------------------------------------------------------------------------//
    // PUT
    //------------------------------------------------------------------------------//

    @Override
    @Transactional
    public Reponse update(Invoice obj) {
        try {
            // ----------------------------------------- //
            // récupération en base avant update         //
            // ----------------------------------------- //
            DAO_Invoice dao_invoice = getDAO(obj.getId());
            if (dao_invoice == null) {
                LOGGER.error("DAO_Invoice null/ID = " + obj.getId().toString());
                return new Reponse(HttpStatus.NOT_FOUND, "BtF_Invoice/update/DAO_Invoice null/ID = " + obj.getId().toString());
            }
            // ----------------------------------------- //
            // mise à jour                               //
            // ----------------------------------------- //
            dao_invoice = dao_invoice
                    .setCode(obj.getCode().substring(0, Math.min(30, obj.getCode().length())))
                    .setLibelle(obj.getLibelle().substring(0, Math.min(255, obj.getLibelle().length())))
                    .setStatus(obj.getStatus())
                    .setCreated(obj.getCreated());
            // ----------------------------------------- //
            repo_Invoice.save(dao_invoice);

            if (trace) LOGGER.warn(dao_invoice.toString());
            return new Reponse(HttpStatus.ACCEPTED, "", dao_invoice.getId());
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return new Reponse(HttpStatus.INTERNAL_SERVER_ERROR, "BtF_Invoice/update " + e.getMessage());
        }
    }

    //------------------------------------------------------------------------------//
    // DELETE
    //------------------------------------------------------------------------------//

    @Override
    @Transactional
    public Reponse delete(Long id) {
        try {
            if (id.equals(0L)) {
                LOGGER.error("id null/ID = " + id.toString());
                return new Reponse(HttpStatus.NOT_FOUND, "BtF_Invoice/delete/id null/ID = " + id.toString());
            }
            repo_Invoice.deleteById(id);
            // ----------------------------------------- //
            if (trace) LOGGER.warn(id.toString());
            return new Reponse(HttpStatus.OK, "", id);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return new Reponse(HttpStatus.INTERNAL_SERVER_ERROR, "BtF_Invoice/delete " + e.getMessage());
        }
    }

    @Override
    @Transactional
    public Reponse delete_Childs(Long id) {
        try {
            if (id.equals(0L)) {
                LOGGER.error("id null/ID = " + id);
                return new Reponse(HttpStatus.NOT_FOUND, "BtF_Invoice/delete_Lines/id null/ID = " + id);
            }
            // ----------------------------------------- //
            // récupération en base avant update         //
            // ----------------------------------------- //
            DAO_Invoice dao_invoice = getDAO(id);
            if (dao_invoice == null) {
                LOGGER.error("DAO_Invoice null/ID = " + id);
                return new Reponse(HttpStatus.NOT_FOUND, "BtF_Invoice/delete_Childs/DAO_Invoice null/ID = " + id);
            }
            // ----------------------------------------- //
            for (Iterator<DAO_InvoiceLine> iter = dao_invoice.getLines().listIterator(); iter.hasNext(); ) {
                DAO_InvoiceLine dao_invoiceLine = iter.next();

                // détruit la jointure //
                dao_invoice.detachLine(dao_invoiceLine);
                // détruit l'objet //
                dbs_Project_invoiceLine.delete(dao_invoiceLine.getId());

                iter.remove();
            }
            // ----------------------------------------- //
            repo_Invoice.save(dao_invoice);

            if (trace) LOGGER.warn(id);
            return new Reponse(HttpStatus.OK, "", id);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return new Reponse(HttpStatus.INTERNAL_SERVER_ERROR, "delete " + e.getMessage());
        }
    }
}
