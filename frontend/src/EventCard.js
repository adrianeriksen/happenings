import React from 'react';

import { Card, CardContent, Typography } from '@material-ui/core';
import { makeStyles } from '@material-ui/styles';
import DateTime from './utils/datetime';

const useStyles = makeStyles({
  card: {
    marginTop: 32,
    marginBottom: 32
  }
});

function EventCard({ event }) {
  const classes = useStyles();

  const { title, startsAt } = event;
  const parsedStartsAt = DateTime(startsAt).toLongFormat();

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

export default EventCard;
