import CalculatorForm from "./CalculatorForm";
import RequirementResults from "./RequirementResults";
import { FormInputs } from "./models/FormInputs";
import { format } from "date-fns";
import { CircularProgress, Container } from "@mui/material";
import { useState } from "react";
import { FetchStatus } from "./models/FetchStatus";

const REQUIREMENTS_URL = "http://localhost:8090/api/trip/requirements";

export default function TripCalculator() {
  const [status, setStatus] = useState<FetchStatus>(FetchStatus.INITIAL);
  const [results, setResults] = useState(null);

  function handleSubmit({ range, conditioning, startDate }: FormInputs) {
    setStatus(FetchStatus.LOADING);

    const formattedDate = format(startDate, "yyyy-MM-dd");

    fetch(
      REQUIREMENTS_URL +
        `?range=${range}&conditioning=${conditioning}&startDate=${formattedDate}`
    )
      .then((response) => response.json())
      .then((results) => {
        setResults(results);
        setStatus(FetchStatus.FINISHED);
      })
      .catch((err) => {
        setStatus(FetchStatus.ERROR);
      });
  }

  return (
    <Container>
      <h1>Trip Calculator</h1>
      <CalculatorForm onSubmit={handleSubmit} fetchStatus={status} />
      {FetchStatus.LOADING === status ? (
        <CircularProgress />
      ) : (
        <RequirementResults tripRequirements={results} fetchStatus={status} />
      )}
    </Container>
  );
}