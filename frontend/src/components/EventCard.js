import React from 'react';
import DateTime from '../utils/datetime';

function EventCard({ event }) {
  const { title, startsAt } = event;
  const parsedStartsAt = DateTime(startsAt).toLongFormat();

  return (
    <div>
      <h2>{title}</h2>
      <p>{parsedStartsAt}</p>
    </div>
  );
}

export default EventCard;
