package ng.com.justjava.bookkeeping.db.entityListener;

import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import ng.com.justjava.bookkeeping.db.GLAccount;

import java.util.Optional;

public class GLAccountListener {
    @PrePersist
    @PreUpdate
    public void setChildrenOfParent(GLAccount glAccount){
        System.out.println(" glAccount parent=="+glAccount.getParent());
        Optional<GLAccount> parent = Optional.ofNullable(glAccount.getParent());
        parent.ifPresent(gl -> {gl.addChild(glAccount);});

    }
}
