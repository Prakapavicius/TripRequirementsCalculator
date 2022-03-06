export interface TripRequirements {
    startDate: string;
    finishDate: string;
    foodRequirements: Requirement[];
    shelterRequirements: Requirement[];
    itemRequirements: Requirement[];
}

export interface Requirement {
    name: string;
    count: number;
}