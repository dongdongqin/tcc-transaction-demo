package com.xxx.api.service;

import com.xxx.api.service.domain.ValidationParameter;

/**
 * validation service
 */
public interface ValidationService {

    /**The same name as the method interface, the first letter capitalized, used to distinguish between authentication scene. For example：@NotNull(groups = ValidationService.Save.class) **/
    /**@GroupSequence: validate the Update group rules at the same time **/
    @interface Save{}
    void save(ValidationParameter parameter);
    void update(ValidationParameter parameter);
}
