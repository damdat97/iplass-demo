package org.iplass.tutorial.exception;

import org.iplass.mtp.ApplicationException;

/**
 * エンティティデータが見つからなかった場合、スローする例外。
 */
public class EntityDataNotFoundException extends ApplicationException {
    private static final long serialVersionUID = 8932829659888789788L;

    public EntityDataNotFoundException() {
        super();
    }

    public EntityDataNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public EntityDataNotFoundException(String message) {
        super(message);
    }

    public EntityDataNotFoundException(Throwable cause) {
        super(cause);
    }
}
