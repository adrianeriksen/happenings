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
    </div>
  );
}

const mapStateToProps = state => ({
  event: state.events.event
});

const mapDispatchToProps = {
  fetchEvent
};

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(Event);
