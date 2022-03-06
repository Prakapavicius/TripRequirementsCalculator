import {Requirement, TripRequirements} from "./models/TripRequirements";
import {
    Grid,
    Table,
    TableBody,
    TableCell,
    TableContainer,
    TableHead,
    TableRow,
    Typography,
} from "@mui/material";
import {FetchStatus} from "./models/FetchStatus";
import {format} from "date-fns";
import "./RequirementResults.css";

export default function RequirementResults({
                                               tripRequirements,
                                               fetchStatus,
                                           }: RequirementResultsProps) {
    if (FetchStatus.INITIAL === fetchStatus) {
        return null;
    }

    if (FetchStatus.ERROR === fetchStatus || !tripRequirements) {
        return <Typography variant="h5">Error Occurred. Try again.</Typography>;
    }

    const {
        startDate,
        finishDate,
        foodRequirements,
        itemRequirements,
        shelterRequirements,
    } = tripRequirements;

    return (
        <Grid container justifyContent="center">
            <Typography variant="h5">Results: </Typography>
            <Grid container item justifyContent="center" className="dates-container">
                <DateResult label="Start date" date={startDate}/>
                <DateResult label="Finish date" date={finishDate}/>
            </Grid>
            <Grid container item justifyContent="space-between" xs={12} md={6}>
                <RequirementResult
                    label="Food requirements"
                    requirements={foodRequirements}
                />
                <RequirementResult
                    label="Item requirements"
                    requirements={itemRequirements}
                />
                <RequirementResult
                    label="Shelter requirements"
                    requirements={shelterRequirements}
                />
            </Grid>
        </Grid>
    );
}

function DateResult({label, date}: DateResultProps) {
    return (
        <Grid item xs={6} md={4}>
            <Typography sx={{fontWeight: 'bold'}}>{label}</Typography>
            <Typography>{format(new Date(date), "yyyy-MM-dd")}</Typography>
        </Grid>
    );
}


function RequirementResult({label, requirements}: RequirementResultProps) {
    if (!requirements?.length) {
        return null;
    }

    return (
        <Grid item xs={12} md={5} marginY={3}>
            <Typography sx={{fontWeight: 'bold'}}>{label}</Typography>
            <TableContainer>
                <Table>
                    <TableHead>
                        <TableRow>
                            <TableCell sx={{fontWeight: 'bold'}}>Name</TableCell>
                            <TableCell sx={{fontWeight: 'bold'}}>Count</TableCell>
                        </TableRow>
                    </TableHead>
                    <TableBody>
                        {requirements.filter(item => item.count > 0).map(({name, count}, index) => (
                            <TableRow key={name}>
                                <TableCell>{name}</TableCell>
                                <TableCell>{count}</TableCell>
                            </TableRow>
                        ))}
                    </TableBody>
                </Table>
            </TableContainer>
        </Grid>
    );
}

interface RequirementResultsProps {
    tripRequirements: TripRequirements | null;
    fetchStatus: FetchStatus;
}

interface DateResultProps {
    label: string;
    date: string;
};

interface RequirementResultProps {
    label: string;
    requirements: Requirement[];
}