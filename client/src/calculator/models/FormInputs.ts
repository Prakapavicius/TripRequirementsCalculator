export interface FormInputs {
    range: number;
    conditioning: ConditioningLevel;
    startDate: Date;
}

export enum ConditioningLevel {
    COUCH_POTATO = "Couch Potato",
    REGULAR = "Regular",
    ADVANCED = "Advanced"
}