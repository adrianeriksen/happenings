import React, { useEffect, useState } from 'react';

import EventCard from './EventCard';

function Events() {
  const [events, setEvents] = useState([]);

  useEffect(() => {
    fetch('/api/events')
      .then(res => res.json())
      .then(res => setEvents(res));
  });

  return events.map(event => <EventCard key={event.id} event={event} />);
}

export default Events;
