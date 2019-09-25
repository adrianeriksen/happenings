import React, { useEffect } from 'react';
import { connect } from 'react-redux';

import EventCard from './EventCard';
import { fetchEvents } from '../actions/events';

function Events({ events, fetchEvents }) {
  useEffect(() => {
    fetchEvents();
  }, [fetchEvents]);

  return events.map(event => <EventCard key={event.id} event={event} />);
}

const mapStateToProps = state => ({
  events: state.events.events
});

const mapDispatchToProps = {
  fetchEvents
};

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(Events);
