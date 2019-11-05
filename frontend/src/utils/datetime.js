import { format } from 'date-fns';

export default function DateTime(timestamp) {
  const date = new Date(timestamp);

  return {
    toLongFormat: () => format(date, 'dd.MM.yyyy HH:mm')
  };
}
