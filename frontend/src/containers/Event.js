import React, { useEffect } from 'react';
import { connect } from 'react-redux';

import { fetchEvent } from '../actions/events';
import DateTime from '../utils/datetime';

function Event({
  event,
  fetchEvent,
  isAuthenticated,
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

  const sendEventResponse = response => () => {
    const fetchOptions = {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify({ status: response })
    };

    fetch(`/api/events/${id}/response`, fetchOptions).then(() =>
      fetchEvent(id)
    );
  };

  const parsedStartsAt = DateTime(event.startsAt).toLongFormat();
  const parsedEndsAt = event.endsAt
    ? DateTime(event.endsAt).toLongFormat()
    : null;

  const authors = event.eventResponses
    .filter(response => response.status === 'HOST')
    .map(response => response.userName);

  const attending = event.eventResponses
    .filter(
      response => response.status === 'HOST' || response.status === 'ACCEPTED'
    )
    .map(response => response.userName);

  const invited = event.eventResponses
    .filter(response => response.status === 'INVITED')
    .map(response => response.userName);

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
        <dd>{authors.join(', ')}</dd>
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

      {isAuthenticated && (
        <>
          <button
            className="alt-colour response-status-btn"
            onClick={sendEventResponse('ACCEPTED')}
          >
            Attend
          </button>
          <button
            className="response-status-btn"
            onClick={sendEventResponse('DECLINED')}
          >
            Don't Attend
          </button>
        </>
      )}

      {event.description && <p>{event.description}</p>}

      {attending.length > 0 && (
        <>
          <h3>Attending</h3>
          <ul>
            {attending.map((name, i) => (
              <li key={i}>{name}</li>
            ))}
          </ul>
        </>
      )}

      {invited.length > 0 && (
        <>
          <h3>Invited</h3>
          <ul>
            {invited.map((name, i) => (
              <li key={i}>{name}</li>
            ))}
          </ul>
        </>
      )}
    </div>
  );
}

const mapStateToProps = state => ({
  event: state.events.event,
  isAuthenticated: state.auth.isAuthenticated
});

const mapDispatchToProps = {
  fetchEvent
};

export default connect(mapStateToProps, mapDispatchToProps)(Event);
