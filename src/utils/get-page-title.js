import defaultSettings from '@/settings'

const title = defaultSettings.title || '新冠疫情监测系统'

export default function getPageTitle(pageTitle) {
  if (pageTitle) {
    return `${pageTitle} - ${title}`
  }
  return `${title}`
}
