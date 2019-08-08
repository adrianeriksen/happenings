import React from "react";
import moment from "moment";

function Event({ event }) {
  const { title, startsAt } = event;
  const parsedStartsAt = moment(startsAt).format("dddd, D. MMMM YYYY, HH:mm");

  return (
    <article>
      <h1>{title}</h1>
      <p>{parsedStartsAt}</p>
    </article>
  );
}

export default Event;
