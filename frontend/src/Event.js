import React from "react";
import moment from "moment";

import { Card, CardContent, Typography } from "@material-ui/core";
import { makeStyles } from "@material-ui/styles";

const useStyles = makeStyles({
  card: {
    marginTop: 32,
    marginBottom: 32
  }
});

function Event({ event }) {
  const classes = useStyles();

  const { title, startsAt } = event;
  const parsedStartsAt = moment(startsAt).format("dddd, D. MMMM YYYY, HH:mm");

  return (
    <Card className={classes.card}>
      <CardContent>
        <Typography variant="h5" component="h2">
          {title}
        </Typography>
        <Typography color="textSecondary">{parsedStartsAt}</Typography>
      </CardContent>
    </Card>
  );
}

export default Event;
