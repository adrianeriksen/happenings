import React, { useEffect, useState } from 'react';

import { Container, Typography } from '@material-ui/core';
import Event from './Event';

function App() {
  const [events, setEvents] = useState([]);

  useEffect(() => {
    fetch('/api/events')
      .then(res => res.json())
      .then(res => setEvents(res));
  });

  return (
    <Container maxWidth="sm">
      <Typography variant="h2" component="h1">
        Happenings
      </Typography>
      {events.map(event => (
        <Event event={event} />
      ))}
    </Container>
  );
}

export default App;
