import React from 'react';
import { Link } from 'react-router-dom';
import DateTime from '../../utils/datetime';

function EventCard({ event }) {
  const { id, title, startsAt } = event;
  const parsedStartsAt = DateTime(startsAt).toLongFormat();

  return (
    <div>
      <h2>
        <Link to={`/events/${id}`}>{title}</Link>
      </h2>
      <p>{parsedStartsAt}</p>
    </div>
  );
}

export default EventCard;
