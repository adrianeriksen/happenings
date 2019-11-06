import React, { useEffect } from 'react';
import { connect } from 'react-redux';

import EventCard from '../components/events/EventCard';
import { createEvent, fetchEvents } from '../actions/events';
import CreateEventFrom from '../components/events/CreateEventForm';

function Events({ events, createEvent, fetchEvents, isAuthenticated }) {
  useEffect(() => {
    fetchEvents();
  }, [fetchEvents]);

  return (
    <>
      {events.map(event => (
        <EventCard key={event.id} event={event} />
      ))}
      {isAuthenticated && <CreateEventFrom createEvent={createEvent} />}
    </>
  );
}

const mapStateToProps = state => ({
  isAuthenticated: state.auth.isAuthenticated,
  events: state.events.events
});

const mapDispatchToProps = {
  createEvent,
  fetchEvents
};

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(Events);
