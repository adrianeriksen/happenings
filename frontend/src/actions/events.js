import { createAction } from 'redux-starter-kit';

export const setEvent = createAction('events/setEvent');
export const setEvents = createAction('events/setEvents');

export const createEvent = event => dispatch => {
  const fetchOptions = {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(event)
  };

  fetch('/api/events', fetchOptions).then(_ => dispatch(fetchEvents()));
};

export const fetchEvents = () => dispatch => {
  fetch('/api/events')
    .then(res => res.json())
    .then(res => dispatch(setEvents(res)));
};

export const fetchEvent = id => dispatch => {
  fetch(`/api/events/${id}`)
    .then(res => res.json())
    .then(res => dispatch(setEvent(res)));
};
