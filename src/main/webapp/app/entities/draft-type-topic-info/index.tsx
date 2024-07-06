import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import DraftTypeTopicInfo from './draft-type-topic-info';
import DraftTypeTopicInfoDetail from './draft-type-topic-info-detail';
import DraftTypeTopicInfoUpdate from './draft-type-topic-info-update';
import DraftTypeTopicInfoDeleteDialog from './draft-type-topic-info-delete-dialog';

const DraftTypeTopicInfoRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<DraftTypeTopicInfo />} />
    <Route path="new" element={<DraftTypeTopicInfoUpdate />} />
    <Route path=":id">
      <Route index element={<DraftTypeTopicInfoDetail />} />
      <Route path="edit" element={<DraftTypeTopicInfoUpdate />} />
      <Route path="delete" element={<DraftTypeTopicInfoDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default DraftTypeTopicInfoRoutes;
