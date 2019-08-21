import { ZonedDateTime, DateTimeFormatter } from 'js-joda';
import { Locale } from '@js-joda/locale_en';

export default function DateTime(rawTimestamp) {
  const timestamp = ZonedDateTime.parse(rawTimestamp);

  const longFormatter = DateTimeFormatter.ofPattern(
    'EEEE, d. MMMM, H:mm'
  ).withLocale(Locale.US);

  return {
    toLongFormat: () => timestamp.format(longFormatter)
  };
}
