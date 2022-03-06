import {ConditioningLevel} from "../models/FormInputs";

// In case we add localisation
export function getConditioningLevelLabel(level: ConditioningLevel) {
    switch (level) {
        case ConditioningLevel.COUCH_POTATO:
            return "Couch potato"
        case ConditioningLevel.REGULAR:
            return "Regular"
        case ConditioningLevel.ADVANCED:
            return "Advanced"
        default:
            return "";
    }
}