package com.gmail.mistle.ibo.travelagency.web.validator;

import com.gmail.mistle.ibo.travelagency.exceptions.ValidationException;
import com.gmail.mistle.ibo.travelagency.web.dto.TourCreationDto;

public class TourCreationValidator {
    private static final String EMPTY_PROPERTY_EXCEPTION_MESSAGE = "Tour creation field parameter '%s' must be provided";


    /**
     * Validate tour creation data
     * @param tourCreationDto set of tour creation parameters
     * @throws ValidationException while at least one of parameters is null or default value
     * @see TourCreationDto
     */
    public static void validate(TourCreationDto tourCreationDto) throws ValidationException {
        validateNotEmptyProperty(tourCreationDto.getTourName(), "name");
        validateNotEmptyProperty(tourCreationDto.getTourDescription(), "description");
        validateNotEmptyProperty(tourCreationDto.getTourCountry(), "country");
        validateNotEmptyProperty(tourCreationDto.getTourHotel(), "hotel");
        validateNotEmptyProperty(tourCreationDto.getTourType(), "type");

        if (tourCreationDto.getTourPrice() == 0 || tourCreationDto.getTourSize() == 0) {
            throw new ValidationException(String.format(EMPTY_PROPERTY_EXCEPTION_MESSAGE, "price or group size"));
        }
    }

    /**
     * Check target variable on null or emptiness
     * @param value target variable
     * @param propertyName variable definition
     */
    private static void validateNotEmptyProperty(String value, String propertyName) {
        if (value == null ||  value.equals("")) {
            throw new ValidationException(String.format(EMPTY_PROPERTY_EXCEPTION_MESSAGE, propertyName));
        }
    }
}
