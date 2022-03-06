import {Controller, useForm} from "react-hook-form";
import React from "react";
import {ConditioningLevel, FormInputs} from "./models/FormInputs";
import {
    Accordion,
    AccordionDetails,
    AccordionSummary,
    Button,
    Container,
    Grid,
    MenuItem,
    TextField,
    Typography,
} from "@mui/material";
import ExpandMoreIcon from "@mui/icons-material/ExpandMore";
import AdapterDateFns from "@mui/lab/AdapterDateFns";
import LocalizationProvider from "@mui/lab/LocalizationProvider";
import DatePicker from "@mui/lab/DatePicker";
import "./CalculatorForm.css";
import {FetchStatus} from "./models/FetchStatus";

const CURRENT_DATE = new Date();

export default function CalculatorForm({onSubmit, fetchStatus}: CalculatorInputFormProps) {
    const {
        register,
        handleSubmit,
        control,
        formState: {errors},
    } = useForm<FormInputs>();

    return (
        <Container maxWidth="sm" className="container">
            <form onSubmit={handleSubmit(onSubmit)}>
                <Grid container justifyContent="flex-start">
                    <Grid item container justifyContent="space-between">
                        <Grid item height={52}>
                            <TextField
                                type="number"
                                label="Range"
                                size="small"
                                placeholder="Range in km"
                                {...register("range", {
                                    min: {value: 1, message: "Range must be more than 0"},
                                    max: {value: 1000, message: "Range must be less than 1000"},
                                    required: "Range is required"
                                })}
                                defaultValue={100}
                                error={!!errors.range}
                                helperText={errors.range?.message}
                                className="range-input"
                            />
                        </Grid>
                        <Grid item height={52}>
                            <Button variant="contained"
                                    type="submit"
                                    size="large"
                                    disabled={FetchStatus.LOADING === fetchStatus}
                                    className="submit">
                                Submit
                            </Button>
                        </Grid>
                    </Grid>
                    <Grid item container justifyContent="flex-start" marginTop={2}>
                        <Accordion className="accordion" color="primary">
                            <AccordionSummary expandIcon={<ExpandMoreIcon/>}>
                                <Typography>Additional details</Typography>
                            </AccordionSummary>
                            <AccordionDetails>
                                <Grid container justifyContent="space-between">
                                    <Grid item xs={5}>
                                        <Controller
                                            render={({field}) => (
                                                <>
                                                    <TextField select
                                                               {...field}
                                                               label="Conditioning"
                                                               placeholder="Select your conditioning"
                                                               fullWidth
                                                    >
                                                        {Object.values(ConditioningLevel).map((level) => (
                                                            <MenuItem key={level} value={level}>
                                                                {level}
                                                            </MenuItem>
                                                        ))}
                                                    </TextField>
                                                </>
                                            )}
                                            defaultValue={ConditioningLevel.REGULAR}
                                            control={control}
                                            name="conditioning"
                                        />
                                    </Grid>
                                    <Grid item xs={5}>
                                        <LocalizationProvider dateAdapter={AdapterDateFns}>
                                            <Controller
                                                render={({field}) => (
                                                    <DatePicker
                                                        {...field}
                                                        label="Start date"
                                                        disablePast
                                                        renderInput={(params) => <TextField {...params} />}
                                                    />
                                                )}
                                                defaultValue={CURRENT_DATE}
                                                control={control}
                                                name="startDate"
                                            />
                                        </LocalizationProvider>
                                    </Grid>
                                </Grid>
                            </AccordionDetails>
                        </Accordion>
                    </Grid>
                </Grid>
            </form>
        </Container>
    );
}

interface CalculatorInputFormProps {
    onSubmit: (values: FormInputs) => void;
    fetchStatus: FetchStatus;
}