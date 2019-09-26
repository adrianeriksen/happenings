import { createAction } from 'redux-starter-kit';

export const setEvents = createAction('events/setEvents');

export const fetchEvents = () => dispatch => {
  fetch('/api/events')
    .then(res => res.json())
    .then(res => dispatch(setEvents(res)));
};
