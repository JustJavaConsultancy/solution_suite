package ng.com.justjava.bookkeeping.db.entityListener;

import jakarta.persistence.PostPersist;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import ng.com.justjava.bookkeeping.db.GLAccount;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

public class GLAccountListener {
    @PrePersist
    @PreUpdate
    public void setChildrenOfParent(GLAccount glAccount){
        System.out.println("PrePersist... The ID at this stage ==="+ glAccount.getId());
        AtomicReference<String> autoCode = new AtomicReference<>(glAccount.getGlHeader().getCode());
        Optional<GLAccount> parent = Optional.ofNullable(glAccount.getParent());
        parent.ifPresent(glparent -> {
            glparent.addChild(glAccount);
            glAccount.setGlHeader(parent.get().getGlHeader());
            autoCode.set(autoCode.get() + glparent.getCode());
        });
        autoCode.set(autoCode.get()+glAccount.getId());
        glAccount.setCode(autoCode.get());

        String xml = """
                <?xml version="1.0" encoding="utf-8" ?>
                """;
    }
}
