import React, { useEffect } from 'react';
import { connect } from 'react-redux';

import { fetchEvent } from '../actions/events';

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

  return (
    <div>
      <h2>{event.title}</h2>
      <dl>
        <dt>Starts at</dt>
        <dd>{event.startsAt}</dd>
        {event.endsAt && (
          <>
            <dt>Ends at</dt>
            <dd>{event.endsAt}</dd>
          </>
        )}
        <dt>Created by ID</dt>
        <dd>{event.createdBy}</dd>
        {event.where && (
          <>
            <dt>Where</dt>
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
