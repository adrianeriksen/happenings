import React, { useEffect } from 'react';
import { connect } from 'react-redux';

import { fetchEvent } from '../actions/events';
import DateTime from '../utils/datetime';

function Event({
  event,
  fetchEvent,
  match: {
    params: { id }
  }
}) {
  useEffect(() => {
    fetchEvent(id);
  }, [fetchEvent, id]);

  if (!event) {
    return null;
  }

  const parsedStartsAt = DateTime(event.startsAt).toLongFormat();
  const parsedEndsAt = event.endsAt
    ? DateTime(event.endsAt).toLongFormat()
    : null;

  return (
    <div>
      <h2>{event.title}</h2>
      <dl className="event-details">
        <dt>
          <i className="far fa-clock" aria-hidden="true" />
          <span className="sr-only">Time of event:</span>
        </dt>
        <dd>
          {parsedStartsAt}
          {event.endsAt && ` to ${parsedEndsAt}`}
        </dd>
        <dt>
          <i className="fas fa-poo" aria-hidden="true" />
          <span className="sr-only">Event created by:</span>
        </dt>
        <dd>{event.createdByName}</dd>
        {event.where && (
          <>
            <dt>
              <i className="fas fa-map-marker-alt" aria-hidden="true" />
              <span className="sr-only">Location of event:</span>
            </dt>
            <dd>{event.where}</dd>
          </>
        )}
      </dl>

      {event.description && <p>{event.description}</p>}
    </div>
  );
}

const mapStateToProps = state => ({
  event: state.events.event
});

const mapDispatchToProps = {
  fetchEvent
};

export default connect(mapStateToProps, mapDispatchToProps)(Event);
