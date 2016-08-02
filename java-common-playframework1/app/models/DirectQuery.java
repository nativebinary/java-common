package models;

public class DirectQuery {
//    private static int executeUpdateCaught(String query, Object... arrayParam) {
//        try {
//            final int countAffectedRow = QueryExecutor.executeUpdate(query, arrayParam);
//            Logger.ri(1, countAffectedRow);
//            return countAffectedRow;
//        }
//        catch (SQLException e) {
//            Logger.e(e);
//            return -1;
//        }
//    }
//
//    public static int adjustStoryDownload() {
//        return executeUpdateCaught(
//                "UPDATE                                                                   " +
//                "    Story M                                                              " +
//                "    INNER JOIN (                                                         " +
//                "        SELECT                                                           " +
//                "            M.id,                                                        " +
//                "            COUNT(*) cnt                                                 " +
//                "            FROM Story M                                                 " +
//                "            INNER JOIN AccountStoryDownload AMD ON M.id = AMD.story      " +
//                "            GROUP BY M.id                                                " +
//                "            ORDER BY cnt DESC                                            " +
//                "    ) AMD2 ON M.id = AMD2.id                                             " +
//                "    SET                                                                  " +
//                "        countDownload = AMD2.cnt;                                        " +
//                "                                                                         " +
//                "SET @rownum := 0;                                                        " +
//                "UPDATE                                                                   " +
//                "    Story M                                                              " +
//                "    SET                                                                  " +
//                "        rankCountDownload = @rownum := (@rownum + 1)                     " +
//                "    ORDER BY M.countDownload ASC;                                        " +
//                ""
//        );
//    }
//
//
//    public static int adjustStoryLike() {
//        return executeUpdateCaught(
//                "UPDATE                                                               " +
//                "    Story M                                                          " +
//                "    INNER JOIN (                                                     " +
//                "        SELECT                                                       " +
//                "            M.id,                                                    " +
//                "            COUNT(*) cnt                                             " +
//                "            FROM Story M                                             " +
//                "            INNER JOIN AccountStoryLike AMF ON M.id = AMF.story      " +
//                "            GROUP BY M.id                                            " +
//                "    ) AMF2 ON M.id = AMF2.id                                         " +
//                "    SET                                                              " +
//                "        countLike = AMF2.cnt;                                        " +
//                "                                                                     " +
//                "SET @rownum := 0;                                                    " +
//                "UPDATE                                                               " +
//                "    Story M                                                          " +
//                "    SET                                                              " +
//                "        rankCountLike = @rownum := (@rownum + 1)                     " +
//                "    ORDER BY M.countLike ASC;                                        " +
//                "                                                                     " +
//                ""
//        );
//    }
//
//    public static int adjustStoryFeatured() {
//        return executeUpdateCaught(
//                "SET @rownum := 0;                                                                                  " +
//                "UPDATE                                                                                             " +
//                "    Story M                                                                                        " +
//                "    SET                                                                                            " +
//                "        rankScoreFeatured = CASE scoreFeatured WHEN 0 THEN 0 ELSE @rownum := (@rownum + 1) END     " +
//                "    ORDER BY M.scoreFeatured ASC;                                                                  " +
//                ""
//        );
//    }
//
//    public static int adjustStoryScoreTotal() {
//        return executeUpdateCaught(
//                "UPDATE                                                           " +
//                "    Story M                                                      " +
//                "    SET                                                          " +
//                "        scoreTotal = countDownload + countLike + scoreFeatured;  " +
//                "                                                                 " +
//                "SET @rownum := 0;                                                " +
//                "UPDATE                                                           " +
//                "    Story M                                                      " +
//                "    SET                                                          " +
//                "        rankScoreTotal = @rownum := (@rownum + 1)                " +
//                "    ORDER BY M.scoreTotal ASC;                                   " +
//                ""
//        );
//    }
//
//    public static int updateStoryFeatured(long id, boolean featured) {
//        final int scoreFeatured = featured ? 1 : 0;
//        return updateStoryFeatured(id, scoreFeatured);
//    }
//
//    public static int updateStoryFeatured(long id, int scoreFeatured) {
//        try {
//            final int countAffectedRow = new Query().eq("id", id).set("scoreFeatured", scoreFeatured).update(Story.class);
//            Logger.i(countAffectedRow);
//            return countAffectedRow;
//        }
//        catch (SQLException e) {
//            Logger.e(e);
//            return -1;
//        }
//    }
//
//    public static int updateStoryBlinded(long id, boolean blinded) {
//        final int scoreFeatured = blinded ? 1 : 0;
//        return updateStoryBlinded(id, scoreFeatured);
//    }
//
//    public static int updateStoryBlinded(long id, int blinded) {
//        try {
//            final int countAffectedRow = new Query().eq("id", id).set("blinded", blinded).update(Story.class);
//            Logger.i(countAffectedRow);
//            return countAffectedRow;
//        }
//        catch (SQLException e) {
//            Logger.e(e);
//            return -1;
//        }
//    }
//
//    public static int updateStoryScoreFeaturedRankScoreFeatured(long id, int scoreFeatured) {
//        try {
//            final int countAffectedRow = new Query().eq("id", id).set("scoreFeatured", scoreFeatured).set("rankScoreFeatured", scoreFeatured).update(Story.class);
//            Logger.i(countAffectedRow);
//            return countAffectedRow;
//        }
//        catch (SQLException e) {
//            Logger.e(e);
//            return -1;
//        }
//    }
//
//    public static int updateStoryDeleted(long id, boolean deleted) {
//        try {
//            final int countAffectedRow = new Query().eq("id", id).set("deleted", deleted).update(Story.class);
//            Logger.i(countAffectedRow);
//            return countAffectedRow;
//        }
//        catch (SQLException e) {
//            Logger.e(e);
//            return -1;
//        }
//    }
//
//    public static int updateStoryFeaturedAll0() {
//        try {
//            final int countAffectedRow = new Query().set("scoreFeatured", 0).set("rankScoreFeatured", 0).gt("rankScoreFeatured", 0).update(Story.class);
//            Logger.i(countAffectedRow);
//            return countAffectedRow;
//        }
//        catch (SQLException e) {
//            Logger.e(e);
//            return -1;
//        }
//    }
//
//    public static int adjustStoryCountComment() {
//        return executeUpdateCaught(
//            "UPDATE                                                               " +
//            "    Story M                                                          " +
//            "    INNER JOIN (                                                     " +
//            "        SELECT                                                       " +
//            "            M.id,                                                    " +
//            "            COUNT(*) cnt                                             " +
//            "            FROM Story M                                             " +
//            "            INNER JOIN AccountStoryComment AMC ON M.id = AMC.story   " +
//            "            GROUP BY M.id                                            " +
//            "    ) AMC2 ON M.id = AMC2.id                                         " +
//            "    SET                                                              " +
//            "        countComment = AMC2.cnt                                      " +
//            ""
//        );
//    }
//
//    public static int adjustAccountCountFollowee() {
//        return executeUpdateCaught(
//                "UPDATE                                      " +
//                        "    Account A                               " +
//                        "    INNER JOIN (                            " +
//                        "        SELECT                              " +
//                        "            accountFollower, COUNT(*) cnt   " +
//                        "            FROM Follow                     " +
//                        "            GROUP BY accountFollower        " +
//                        "    )  F ON A.id = F.accountFollower        " +
//                        "    SET                                     " +
//                        "        countFollowee = F.cnt               " +
//                        ""
//        );
//    }
//
//    public static int adjustAccountCountFollower() {
//        return executeUpdateCaught(
//                "UPDATE                                      " +
//                "    Account A                               " +
//                "    INNER JOIN (                            " +
//                "        SELECT                              " +
//                "            accountFollowee, COUNT(*) cnt   " +
//                "            FROM Follow                     " +
//                "            GROUP BY accountFollowee        " +
//                "    )  F ON A.id = F.accountFollowee        " +
//                "    SET                                     " +
//                "        countFollower = F.cnt               " +
//                        ""
//        );
//    }
//
//    public static int adjustAccountCountStoryDownloadedCountStoryLikedScoreRank() {
//        return executeUpdateCaught(
//                "UPDATE                                                        " +
//                "	Account A                                                  " +
//                "	INNER JOIN (                                               " +
//                "		SELECT                                                 " +
//                "			account, SUM(countDownload) cd, SUM(countLike) cl  " +
//                "			FROM Story                                         " +
//                "			GROUP BY account                                   " +
//                "	) S ON A.id = S.account                                    " +
//                "	SET                                                        " +
//                "		countStoryDownloaded = S.cd,                           " +
//                "		countStoryLiked = S.cl,                                " +
//                "		score = countFollower + S.cd + S.cl;                   " +
//                "                                                              " +
//                "SET @rownum := 0;                                             " +
//                "UPDATE                                                        " +
//                "	Account A                                                  " +
//                "	SET                                                        " +
//                "		rank = @rownum := (@rownum+1)                          " +
//                "	ORDER BY score DESC;                                       " +
//                ""
//        );
//    }
//
//
//    public static int updateStoryCountCommentIncrease(long id, int value) {
//        try {
//            final int countAffectedRow = QueryExecutor.executeUpdate(
//                    "UPDATE                                      " +
//                    "    Story M                                 " +
//                    "    SET                                     " +
//                    "        countComment = M.countComment + ?   " +
//                    "	WHERE id = ?                             " +
//                    "", value, id
//            );
//
//            if(countAffectedRow != 1) {
//                Logger.e(countAffectedRow);
//            }
//            return countAffectedRow;
//        }
//        catch (SQLException e) {
//            Logger.e(e);
//            return -1;
//        }
//    }
//
//    public static int updateStoryCountLikeIncrease(long id, int value) {
//        try {
//            final int countAffectedRow = QueryExecutor.executeUpdate(
//                    "UPDATE                                      " +
//                            "    Story M                                 " +
//                            "    SET                                     " +
//                            "        countLike = M.countLike + ?   " +
//                            "	WHERE id = ?                             " +
//                            "", value, id
//            );
//
//            if(countAffectedRow != 1) {
//                Logger.e(countAffectedRow);
//            }
//            return countAffectedRow;
//        }
//        catch (SQLException e) {
//            Logger.e(e);
//            return -1;
//        }
//    }
//
//    public static List<Story> selectListStory(long until, StoryOrderBy storyOrderBy, long accountIdOwner, String keyword, long categoryId, long accountIdFollower, boolean isAdmin) {
//        try {
//            List<Object> listParameter = new ArrayList<Object>();
//            StringBuilder sb = new StringBuilder();
//            sb.append("SELECT                                                                 ");
//            sb.append("    S.*                                                                ");
//            sb.append("    FROM Story S                                                       ");
//
//            if(categoryId != 0)
//            {
//                sb.append("    INNER JOIN StoryCategory SC ON S.id = SC.story AND SC.category = ? ");
//                listParameter.add(categoryId);
//            }
//
//            if(accountIdFollower != 0)
//            {
//                sb.append("    INNER JOIN Follow F ON S.account = F.accountFollowee AND F.accountFollower = ? ");
//                listParameter.add(accountIdFollower);
//            }
//
//            {
//                sb.append("    WHERE                                                              ");
//                sb.append("        privacy = 'Public'                                             ");
//                sb.append("        AND deleted = 0                                                ");
//                if(!isAdmin)
//                    sb.append("        AND blinded = 0                                                ");
//            }
//
//            {
//                sb.append("        AND S.").append(storyOrderBy.fieldName).append(" < ?                       ");
//                sb.append("        AND S.").append(storyOrderBy.fieldName).append(" > 0                       ");
//                listParameter.add(until);
//            }
//
//            if(0 != accountIdOwner)
//            {
//                sb.append("        AND account = ?                                                ");
//                listParameter.add(accountIdOwner);
//            }
//
//            if(!StringUtil.isNullOrEmpty(keyword))
//            {
//                final String[] arrayKeyword = keyword.split(" ");
//                for (String s : arrayKeyword) {
//                    sb.append("        AND bodyText LIKE ?		                                  ");
//                    listParameter.add("%" + s + "%");
//                }
//            }
//
//            {
//                sb.append("    ORDER BY S.").append(storyOrderBy.fieldName).append(" DESC                     ");
//            }
//            {
//                sb.append("    LIMIT ?                                                            ");
//                listParameter.add(ApplicationConf.api_result_limit());
//            }
//
//            final String query = sb.toString();
//            final Object[] arrayParam = listParameter.toArray();
//            final PreparedStatement preparedStatement = QueryExecutor.createPreparedStatement(query, arrayParam);
////            Logger.e("---------------------------------------------");
////            Logger.e("\n" + query, StringUtil.join("\t", arrayParam));
////            Logger.e("---------------------------------------------");
//            final ResultSet resultSet = preparedStatement.executeQuery();
//            final List<Map<String,Object>> listMap = ResultSetUtil.getListMap(resultSet);
//            return ReflectionUtil.fromListMap(Story.class, listMap, Id.class);
//        }
//        catch (SQLException e) {
//            Logger.e(e);
//            return new ArrayList<Story>();
//        }
//    }
//    public static int selectStoryCountByCategory(Category category) {
//        try {
//            final ResultSet resultSet = QueryExecutor.executeQuery(
//                    "SELECT COUNT(*)                                             " +
//                    "	FROM StoryCategory SC                                    " +
//                    "	INNER JOIN Story S ON S.id = SC.story                    " +
//                    "                                AND S.deleted = 0           " +
//                    "                                AND S.blinded = 0           " +
//                    "	WHERE SC.category = ?                                    " +
//                    "", category.id
//            );
//            return ResultSetUtil.getScalarInt(resultSet);
//        }
//        catch (SQLException e) {
//            Logger.e(e);
//            return -1;
//        }
//    }
//
//    public static List<Map<String, Object>> selectStoryCountGroupByCategory() {
//        try {
//            final ResultSet resultSet = QueryExecutor.executeQuery(
//                    "SELECT SC.category, COUNT(*) cnt                            " +
//                    "	FROM StoryCategory SC                                    " +
//                    "	INNER JOIN Story S ON S.id = SC.story                    " +
//                    "                                AND S.deleted = 0           " +
//                    "                                AND S.blinded = 0           " +
//                    "	GROUP BY SC.category                                     " +
//                    ""
//            );
//            return ResultSetUtil.getListMap(resultSet);
//        }
//        catch (SQLException e) {
//            Logger.e(e);
//            return new ArrayList<Map<String, Object>>();
//        }
//    }
}
